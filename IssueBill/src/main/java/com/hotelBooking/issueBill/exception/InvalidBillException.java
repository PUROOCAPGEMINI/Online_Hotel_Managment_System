package com.hotelBooking.issueBill.exception;

@SuppressWarnings("serial")
public class InvalidBillException extends RuntimeException{
	public InvalidBillException(String message){
		super(message);
	}
}
