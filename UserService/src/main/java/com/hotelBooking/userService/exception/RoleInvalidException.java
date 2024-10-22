package com.hotelBooking.userService.exception;

public class RoleInvalidException extends RuntimeException {
    public RoleInvalidException(String message) {
        super(message);
    }
}