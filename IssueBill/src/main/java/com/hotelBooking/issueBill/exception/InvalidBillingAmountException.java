package com.hotelBooking.issueBill.exception;

@SuppressWarnings("serial")
public class InvalidBillingAmountException extends RuntimeException{
	public InvalidBillingAmountException(String message) {
		super(message);
	}
}
