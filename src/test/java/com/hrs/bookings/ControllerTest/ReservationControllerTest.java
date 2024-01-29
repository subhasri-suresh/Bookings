package com.hrs.bookings.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.bookings.BookingsApplication;
import com.hrs.bookings.controller.ReservationController;
import com.hrs.bookings.entity.Hotels;
import com.hrs.bookings.entity.Reservation;
import com.hrs.bookings.entity.ReservedRooms;
import com.hrs.bookings.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    //test post method
    public void testCreateReservationValid() throws Exception {
        //add body to post request
        Reservation reservationObj = new Reservation();

        List<ReservedRooms> reservedRoomsList  = new LinkedList<>();
        ReservedRooms reservedRoomsObj = new ReservedRooms();
        reservedRoomsObj.setRoomCount(2);
        reservedRoomsList.add(reservedRoomsObj);

        Hotels hotelsObj = new Hotels();
        hotelsObj.setId(Long.valueOf(1));
        hotelsObj.setHotelName("IBIS");

        reservationObj.setHotel(hotelsObj);
        reservationObj.setUserId(1);
        reservationObj.setReservationStatus(1);
        reservationObj.setReservationStartDate(Date.valueOf("2024-01-29"));
        reservationObj.setReservationEndDate(Date.valueOf("2024-01-30"));

        String requestBody = new ObjectMapper().valueToTree(reservationObj).toString();

        mockMvc.perform(post("/reservation/add").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());
    }

    @Test
    //test delete method
    public void testCancelReservation() throws Exception {
        //add body to post request
        mockMvc.perform(delete("/reservation/cancel/1")).andExpect(status().isOk());
    }



}
