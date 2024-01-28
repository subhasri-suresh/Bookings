package com.hrs.bookings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity(name = "AVAILABLE_ROOMS")
@Getter
@Setter
@RequiredArgsConstructor
public class AvailableRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hotel_id")
    private long hotelId;

    @Column(name = "room_type")
    private int roomType;

    private int count;

    @Column(name = "available_date")
    private Date availableDate;
}
