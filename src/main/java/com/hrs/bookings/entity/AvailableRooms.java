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

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotels hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;

    private int count;

    @Column(name = "available_date")
    private Date availableDate;
}
