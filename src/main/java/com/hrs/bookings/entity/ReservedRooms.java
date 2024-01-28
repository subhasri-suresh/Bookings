package com.hrs.bookings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "RESERVED_ROOMS")
@Getter
@Setter
@RequiredArgsConstructor
public class ReservedRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private long hotelId;

    private int roomType;

    private int roomCount;

}
