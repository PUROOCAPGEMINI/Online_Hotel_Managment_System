package com.hotelBooking.issueBill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBooking.issueBill.models.IssueBill;
import com.hotelBooking.issueBill.service.BillServices;



@RestController
@RequestMapping("/api/billings")
public class BillController {

    @Autowired
    private BillServices service;
    
    @GetMapping("/generate/{paymentId}")
    public IssueBill generateBill(@PathVariable int paymentId) {
        return service.generateBill(paymentId);
    }
}
