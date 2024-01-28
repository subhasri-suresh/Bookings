package com.hrs.bookings.controller;

import com.hrs.bookings.entity.Reservation;
import com.hrs.bookings.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity createReservation(@RequestBody Reservation reservationObj){
        try {
            reservationService.createReservation(reservationObj);
            return ResponseEntity.ok().build();
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelReservation(@PathVariable long id){
        reservationService.cancelReservation(id);
    }

    @PutMapping
    public void updateReservation(){

    }


}
