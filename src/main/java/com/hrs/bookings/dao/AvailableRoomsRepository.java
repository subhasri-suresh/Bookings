package com.hrs.bookings.dao;

import com.hrs.bookings.entity.AvailableRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface AvailableRoomsRepository extends JpaRepository<AvailableRooms, Long> {

    @Query(value = "UPDATE AVAILABLE_ROOMS ar set ar.count = ar.count-1 WHERE ar.id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    public void updateCount(long id);

    public List<AvailableRooms> findByroomTypeAndHotelIdAndAvailableDateBetweenAndCountGreaterThan(int roomType, long hotelId, Date startDate, Date endDate, int minCount);
}
