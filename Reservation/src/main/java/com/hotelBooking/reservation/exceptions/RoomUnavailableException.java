package com.hotelBooking.reservation.exceptions;

@SuppressWarnings("serial")
public class RoomUnavailableException extends Exception {
    public RoomUnavailableException(String message) {
        super(message);
    }
}
