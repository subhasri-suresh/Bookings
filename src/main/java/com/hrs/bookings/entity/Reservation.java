package com.hrs.bookings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

@Entity(name = "RESERVATION")
@Getter
@Setter
@RequiredArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    private Hotels hotel;

    @Column(name = "reservation_time")
    @CreationTimestamp
    private Date reservationTime;

    @Column(name = "reservation_status")
    private int reservationStatus;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservedRooms> reservedRooms;

    @Column(name = "reservation_startDate")
    Date reservationStartDate;

    @Column(name = "reservation_endDate")
    Date reservationEndDate;
}
