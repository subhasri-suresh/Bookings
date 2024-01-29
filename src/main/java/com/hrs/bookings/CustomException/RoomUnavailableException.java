package com.hrs.bookings.CustomException;

public class RoomUnavailableException extends RuntimeException{
    public RoomUnavailableException(String message) {
        super(message);
    }
}
