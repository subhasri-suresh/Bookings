package com.hrs.bookings.constants;

public enum ReservationStatus {
    CANCELED(0),
    RESERVED(1),
    CHECKEDIN(2),
    CHECKEDOUT(3);

    int type;
    ReservationStatus(int type){
    }

    public static int getType(ReservationStatus status){
        return  status.type;
    }
}
