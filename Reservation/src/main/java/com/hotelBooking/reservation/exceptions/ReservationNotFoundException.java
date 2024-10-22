
package com.hotelBooking.reservation.exceptions;

@SuppressWarnings("serial")
public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}

