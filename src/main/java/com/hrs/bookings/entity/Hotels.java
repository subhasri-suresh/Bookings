package com.hrs.bookings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "HOTELS")
@Getter
@Setter
@RequiredArgsConstructor
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "contact_no")
    private String contactNo;

    private String address;

    private String city;

    private String country;

    private String zipcode;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Reservation> reservation;
}
