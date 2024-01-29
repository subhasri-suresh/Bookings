package com.hrs.bookings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "ROOMS")
@Getter
@Setter
@RequiredArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotels hotel;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_type")
    private int roomType;

    @Column(name = "total_rooms")
    private int totalRooms;

}
