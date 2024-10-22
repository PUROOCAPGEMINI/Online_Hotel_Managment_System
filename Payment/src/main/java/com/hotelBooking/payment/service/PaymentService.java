package com.hotelBooking.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBooking.payment.model.Payment;
import com.hotelBooking.payment.repository.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo payRepo;

    public String addPayment(Payment paymentInfo) {
        payRepo.save(paymentInfo); 
        return "Payment added successfully with transaction ID: " + paymentInfo.getTransactionId();
    }

    public Payment getPaymentById(int transactionId) {
        return payRepo.findById(transactionId).orElseThrow(null); 
        
    }
}
