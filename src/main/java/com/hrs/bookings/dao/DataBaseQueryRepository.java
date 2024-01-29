package com.hrs.bookings.dao;

import com.hrs.bookings.entity.Hotels;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class DataBaseQueryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Hotels> getHotels(String city, Date checkinDate, Date checkoutDate, int noOfDays) {
        return (List<Hotels>) entityManager.createNativeQuery("select id, hotel_name, contact_no, address, city, country, zipcode from (SELECT count(distinct AVAILABLE_DATE) days, hotels.id, hotel_name, contact_no, address, city, country, zipcode  FROM HOTELS join AVAILABLE_ROOMS on HOTELS.ID=AVAILABLE_ROOMS.HOTEL_ID where city iLIKE ? and AVAILABLE_ROOMS.AVAILABLE_DATE >= ? and AVAILABLE_ROOMS.AVAILABLE_DATE < ? and count > 0 group by hotels.id) as HOTELS where days = ?", Hotels.class)
                .setParameter(1, city).setParameter(2, checkinDate).setParameter(3, checkoutDate).setParameter(4, noOfDays).getResultList();
    }

    @Transactional
    public void updateReservedRoom(long reservedRoomId, long oldRoomId, long newRoomId, int roomCount) throws Exception {
        entityManager.createNativeQuery("update AVAILABLE_ROOMS  set count=count-? where count>=? and id=?")
                .setParameter(1, roomCount).setParameter(2, roomCount).setParameter(3, newRoomId).executeUpdate();
        entityManager.createNativeQuery("update AVAILABLE_ROOMS  set count=count+? where id=?")
                .setParameter(1, roomCount).setParameter(2, oldRoomId).executeUpdate();
        entityManager.createNativeQuery("update RESERVED_ROOMS  set room_id=? where id=?")
                .setParameter(1, newRoomId).setParameter(2, reservedRoomId).executeUpdate();
    }
}
