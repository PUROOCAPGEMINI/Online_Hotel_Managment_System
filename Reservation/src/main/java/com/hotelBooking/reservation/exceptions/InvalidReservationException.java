package com.hotelBooking.reservation.exceptions;

@SuppressWarnings("serial")
public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException(String message) {
        super(message);
    }
}

