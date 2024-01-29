package com.hrs.bookings.controller;

import com.hrs.bookings.entity.Reservation;
import com.hrs.bookings.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelReservation(@PathVariable long id){
        reservationService.cancelReservation(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReservation(@PathVariable long id, @RequestBody Map requestObj){
        try{
            reservationService.updateReservation(id, requestObj);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
