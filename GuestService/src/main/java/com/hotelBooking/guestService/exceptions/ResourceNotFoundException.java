package com.hotelBooking.guestService.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message)
	{
		super(message);
	}

}
