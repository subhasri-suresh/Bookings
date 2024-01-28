package com.hrs.bookings.dao;

import com.hrs.bookings.entity.ReservedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedRoomsRepository extends JpaRepository<ReservedRooms, Long> {


}
