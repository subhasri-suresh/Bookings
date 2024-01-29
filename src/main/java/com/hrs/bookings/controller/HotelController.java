package com.hrs.bookings.controller;

import com.hrs.bookings.entity.Hotels;
import com.hrs.bookings.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public List<Hotels> getAvailableRooms(@RequestParam("city") String city, @RequestParam("checkin_date") String checkin, @RequestParam("checkout_date") String checkout){
        return hotelService.getHotels(city, checkin, checkout);
    }
}
