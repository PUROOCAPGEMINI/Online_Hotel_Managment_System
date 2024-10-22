package com.hotelBooking.issueBill.service;


import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelBooking.issueBill.models.IssueBill;
import com.hotelBooking.issueBill.models.Payment;
import com.hotelBooking.issueBill.repo.BillRepo;

@Service
public class BillServices {
	@Autowired
	private BillRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	private static final String PAYMENT_SERVICE_URL = "http://localhost:8087";

	

	public IssueBill generateBill(int paymentId) {
		// Fetch payment information from the PaymentService
		 Payment payment = restTemplate.getForObject(PAYMENT_SERVICE_URL+"/payment/"+paymentId, Payment.class);

		    if (payment == null) {
		        throw new RuntimeException("Payment with ID: " + paymentId + " not found.");
		    }

		    double totalAmount = (payment.getTotal_payment()*payment.getNumber_rooms())+((payment.getTotal_payment()*payment.getTaxes())/100);

		    IssueBill bill = new IssueBill();
		    bill.setBillingNumber("BILL-" + payment.getTransactionId()); 
		    bill.setPayment(payment);
		    bill.setTotalAmount(totalAmount);
		    bill.setDate(LocalDate.now());  

		    repo.save(bill);

		    System.out.println("Total payment after tax for transaction ID " + paymentId + " is: " + totalAmount);
		    System.out.println("Bill saved successfully with billing number: " + bill.getBillingNumber());

		    return bill;
	}
}
