package com.hrs.bookings;

public class RoomUnavailableException extends RuntimeException{
    public RoomUnavailableException(String message) {
        super(message);
    }
}
