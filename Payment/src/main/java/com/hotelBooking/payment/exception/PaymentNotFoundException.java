package com.hotelBooking.payment.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(int transactionId) {
        super("Payment not found for transaction ID: " + transactionId);
    }
    
    public PaymentNotFoundException(String mssg) {
        super(mssg);
    }
}
