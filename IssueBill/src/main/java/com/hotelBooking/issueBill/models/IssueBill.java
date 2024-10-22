package com.hotelBooking.issueBill.models;


import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IssueBill {
	@Id
	private String billingNumber;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "transactionId")
	private Payment payment;
	private double totalAmount;
    private LocalDate date;
    
	public String getBillingNumber() {
		return billingNumber;
	}
	public void setBillingNumber(String billingNumber) {
		this.billingNumber = billingNumber;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

		

}
