package com.hrs.bookings.service;

import com.hrs.bookings.CustomException.RoomUnavailableException;
import com.hrs.bookings.constants.ReservationStatus;
import com.hrs.bookings.dao.*;
import com.hrs.bookings.entity.AvailableRooms;
import com.hrs.bookings.entity.Reservation;
import com.hrs.bookings.entity.ReservedRooms;
import com.hrs.bookings.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    HotelRepository hotelRepository;
    AvailableRoomsRepository availableRoomsRepository;
    ReservedRoomsRepository reservedRoomsRepository;

    @Autowired
    DataBaseQueryRepository queryRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    @Autowired
    public void setAvailableRoomsRepository(AvailableRoomsRepository availableRoomsRepository){
        this.availableRoomsRepository = availableRoomsRepository;
    }

    @Autowired
    public void setReservedRoomsRepository(ReservedRoomsRepository reservedRoomsRepository){
        this.reservedRoomsRepository = reservedRoomsRepository;
    }

    Logger logger = LoggerFactory.getLogger(ReservationService.class);


    public void createReservation(Reservation reservationObj) {
        for (ReservedRooms reservedRoom : reservationObj.getReservedRooms()) {
            reservedRoom.setReservation(reservationObj);

        }
        reserveRooms(reservationObj);
    }

    /**
     * Cancels the reservation and updates the available rooms count
     *
     * @param id
     */
    public void cancelReservation(long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservation.setReservationStatus(ReservationStatus.getType(ReservationStatus.CANCELED));
        for (ReservedRooms reservedRoom : reservation.getReservedRooms()) {
            //revert the count of the available rooms
            List<AvailableRooms> availableRooms = availableRoomsRepository.findByHotelIdAndAvailableDateBetween( reservation.getHotel().getId(),
                    reservation.getReservationStartDate(), reservation.getReservationEndDate());
            for (AvailableRooms availableRoom : availableRooms) {
                availableRoom.setCount(availableRoom.getCount() + 1);
                availableRoomsRepository.save(availableRoom);
            }
        }
        reservationRepository.save(reservation);
    }

    /**
     * Verifies if all the selected rooms are available creates the reservation
     * and
     *
     * @param reservationObj
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void reserveRooms(Reservation reservationObj) {
        try {
            List<ReservedRooms> reservedRoomsList = reservationObj.getReservedRooms();
            reservationObj.setReservedRooms(null);
            reservationRepository.save(reservationObj);

            Date startDate = reservationObj.getReservationStartDate();
            Date endDate =  reservationObj.getReservationEndDate();
            Date previousDate = Date.valueOf(DateUtil.getPreviousDateInString(endDate.toString()));
            long totalDays = DateUtil.countDays(startDate.toString(), endDate.toString());

            for (ReservedRooms reservedRoom : reservedRoomsList) {
                List<AvailableRooms> availableRooms = availableRoomsRepository.findByHotelIdAndRoomIdAndAvailableDateBetweenAndCountGreaterThan( reservationObj.getHotel().getId(),
                        reservedRoom.getRoom().getId(), startDate, previousDate, reservedRoom.getRoomCount());

                if(totalDays != availableRooms.size()){
                    throw new RoomUnavailableException("Booking not available. All the rooms are booked");
                }

                for (AvailableRooms availableroom : availableRooms){
                    availableRoomsRepository.updateCount(availableroom.getId());
                }
                reservedRoomsRepository.save(reservedRoom);
            }
        } catch (ParseException ex){
            logger.error("Invalid date",ex.getMessage());
        }
    }

    /**
     * To update reservation rooms type
     *
     * @param id
     */
    public void updateReservation(long id, Map updateObj) throws Exception {
        Reservation reservation = reservationRepository.findById(id);
        if(reservation == null) {
            throw new RuntimeException("Reservation not found");
        }
        long oldRoomId = Long.valueOf(updateObj.get("old_room_id").toString());
        long newRoomId = Long.valueOf(updateObj.get("new_room_id").toString());
        ReservedRooms oldRoomData = reservedRoomsRepository.findByReservation_IdAndRoom_IdEquals(id, oldRoomId);
        if(oldRoomData == null) {
            throw new RuntimeException("Reservation room not found");
        }
        int noOfRooms = oldRoomData.getRoomCount();
        queryRepository.updateReservedRoom(oldRoomData.getId(), oldRoomId, newRoomId, noOfRooms);
    }
}
