package com.hotelBooking.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelBooking.payment.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
