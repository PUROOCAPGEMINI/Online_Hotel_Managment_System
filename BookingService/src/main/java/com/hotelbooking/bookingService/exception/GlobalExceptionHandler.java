package com.hotelbooking.bookingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(value = {RuntimeException.class})
	    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(value = {HttpClientErrorException.class})
	    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {
	        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
	    }

	    @ExceptionHandler(value = {Exception.class})
	    public ResponseEntity<Object> handleGenericException(Exception e) {
	        return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
