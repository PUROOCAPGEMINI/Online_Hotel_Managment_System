package com.hotelBooking.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelBooking.payment.model.Payment;
import com.hotelBooking.payment.service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    PaymentService PayServ;

    @PostMapping("/save")
    public String addPayment(@RequestBody Payment paymentInfo) {
        return PayServ.addPayment(paymentInfo);
    }

    @GetMapping("/payment/{transactionId}")
    public Payment getPayment(@PathVariable int transactionId) {
        return PayServ.getPaymentById(transactionId);
    }
}
