package com.hrs.bookings.constants;

public enum ReservationStatus {
    CANCELED(0),
    ACTIVE(1);

    int type;
    ReservationStatus(int type){
    }

    public static int getType(ReservationStatus status){
        return  status.type;
    }
}
