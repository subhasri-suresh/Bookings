package com.hrs.bookings.dao;

import com.hrs.bookings.entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotels, Long> {

    public List<Hotels> findByZipcodeOrCity(String zipCode, String city);

}
