package com.hrs.bookings.constants;

public enum RoomType {
    DELUXE(1),
    SUITE(2);

    int roomType;
    RoomType(int type){
        this.roomType=type;
    }

}
