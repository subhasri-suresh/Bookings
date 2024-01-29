package com.hrs.bookings.dao;

import com.hrs.bookings.entity.Reservation;
import com.hrs.bookings.entity.ReservedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Reservation findById(long id);

}
