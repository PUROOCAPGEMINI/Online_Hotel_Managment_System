package com.hotelBooking.issueBill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(BillNotFoundException.class)  
	public ResponseEntity<String> handleEmployeeNptFoundException(BillNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidBillException.class) 
	public ResponseEntity<String> handleEmployeeCreationException(InvalidBillException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidBillingAmountException.class) 
	public ResponseEntity<String> handleEmployeeCreationException(InvalidBillingAmountException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	
}

