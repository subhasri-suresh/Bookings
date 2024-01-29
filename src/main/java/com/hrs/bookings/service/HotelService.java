package com.hrs.bookings.service;

import com.hrs.bookings.dao.DataBaseQueryRepository;
import com.hrs.bookings.dao.HotelRepository;
import com.hrs.bookings.entity.Hotels;
import com.hrs.bookings.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    DataBaseQueryRepository queryRepository;

    /**
     * Gets the room availablity based on the city, checkin and checkout dates
     * @param city
     * @param checkin date as string
     * @param checkout date as string
     * @return
     */
    public List<Hotels> getHotels(String city, String checkin, String checkout){

        //TODO dates , roomtype and room count
        Date checkinDate, checkoutDate;
        int noOfDays;
        try {
            checkinDate = Date.valueOf(checkin);
            checkoutDate = Date.valueOf(checkout);
            noOfDays = (int) DateUtil.countDays(checkin, checkout);
        } catch ( Exception ex) {
            return new ArrayList<>();
        }
        return queryRepository.getHotels(city, checkinDate, checkoutDate, noOfDays);
    }

    /**
     * Gets the room availablity based on the zipcode, city, dates , roomtype and room count
     * @param zipCode
     * @param city
     * @return
     */
    public List<Hotels> getAvailableRooms(String zipCode, String city){

        //TODO dates , roomtype and room count
        return hotelRepository.findByZipcodeOrCity(zipCode,city);
    }
}
