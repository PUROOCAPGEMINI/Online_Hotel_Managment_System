package com.hotelBooking.room.exception;

@SuppressWarnings("serial")
public class RoomNotFoundException extends RuntimeException{
	public RoomNotFoundException(int id) {
        super("Room with ID " + id + " not found.");
    }

	public RoomNotFoundException(String message) {
		super(message);
	}
}
