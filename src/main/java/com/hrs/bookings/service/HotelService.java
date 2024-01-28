package com.hrs.bookings.service;

import com.hrs.bookings.dao.HotelRepository;
import com.hrs.bookings.entity.Hotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

        @Autowired
        HotelRepository hotelRepository;

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
