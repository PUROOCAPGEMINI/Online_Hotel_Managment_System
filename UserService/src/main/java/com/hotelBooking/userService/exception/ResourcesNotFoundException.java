package com.hotelBooking.userService.exception;

public class ResourcesNotFoundException extends RuntimeException{
	public ResourcesNotFoundException(String message) {
        super(message);
    }
	
	public ResourcesNotFoundException() {
		super("Exception Occured : Resource not found in the database");
	}
}
