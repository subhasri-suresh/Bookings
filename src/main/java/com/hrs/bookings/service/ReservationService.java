package com.hrs.bookings.service;

import com.hrs.bookings.RoomUnavailableException;
import com.hrs.bookings.constants.ReservationStatus;
import com.hrs.bookings.dao.AvailableRoomsRepository;
import com.hrs.bookings.dao.HotelRepository;
import com.hrs.bookings.dao.ReservationRepository;
import com.hrs.bookings.dao.ReservedRoomsRepository;
import com.hrs.bookings.entity.AvailableRooms;
import com.hrs.bookings.entity.Hotels;
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


@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    AvailableRoomsRepository availableRoomsRepository;

    @Autowired
    ReservedRoomsRepository reservedRoomsRepository;

    Logger logger = LoggerFactory.getLogger(ReservationService.class);


    public void createReservation(Reservation reservationObj) {
        Hotels hotel = hotelRepository.getReferenceById(reservationObj.getHotel().getId());
        reservationObj.setHotel(hotel);
        for (ReservedRooms reservedRoom : reservationObj.getReservedRooms()) {
            reservedRoom.setReservation(reservationObj);

        }
        reserveRooms(reservationObj);
    }

    public void cancelReservation(long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservation.setReservationStatus(ReservationStatus.getType(ReservationStatus.CANCELED));
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
            long totalDays = DateUtil.countDays(startDate.toString(), endDate.toString());

            for (ReservedRooms reservedRoom : reservedRoomsList) {
                //LocalDate date = LocalDate.parse(endDate.toString());
                // Date finalEndDate = date.min
                List<AvailableRooms> availableRooms = availableRoomsRepository.findByroomTypeAndHotelIdAndAvailableDateBetweenAndCountGreaterThan(reservedRoom.getRoomType(), reservedRoom.getHotelId(),
                        startDate, endDate, 0);

                if(totalDays != availableRooms.size()-1){
                    throw new RoomUnavailableException("Booking not available. All the rooms are booked");
                }

                for (AvailableRooms availableroom : availableRooms){
                    availableRoomsRepository.updateCount(availableroom.getId());
                }
                reservedRoomsRepository.save(reservedRoom);
            }
        } catch (ParseException ex){
            logger.error("Invalid date",ex.getMessage());
        } catch (Exception ex){
            logger.error("Exception"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
