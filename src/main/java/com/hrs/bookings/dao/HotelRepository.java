package com.hrs.bookings.dao;

import com.hrs.bookings.entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotels, Long> {

    public List<Hotels> findByCity(String city);

//    public List<Hotels> findByCityAndAvailableRooms_AvailableDateBetween(String city, Date checkinDate, Date checkoutDate);

    public List<Hotels> findByZipcodeOrCity(String zipCode, String city);

}
