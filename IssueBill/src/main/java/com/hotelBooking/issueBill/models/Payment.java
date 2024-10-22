package com.hotelBooking.issueBill.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payment {

	@Id
	private int transactionId;
	private double Total_payment;
	private int number_rooms;
	private String modeOfPayment;
	private double taxes = 7.50;
	
	public Payment() {}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTotal_payment() {
		return Total_payment;
	}
	public void setTotal_payment(double total_payment) {
		Total_payment = total_payment;
	}
	public int getNumber_rooms() {
		return number_rooms;
	}
	public void setNumber_rooms(int number_rooms) {
		this.number_rooms = number_rooms;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public Payment(int transactionId, double total_payment, int number_rooms, String modeOfPayment, double taxes) {
		super();
		this.transactionId = transactionId;
		Total_payment = total_payment;
		this.number_rooms = number_rooms;
		this.modeOfPayment = modeOfPayment;
		this.taxes = taxes;
	}
	
	


	
	
}
