package com.hotelBooking.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payment {

	@Id
	private int transactionId;
	private double price;
	private int number_rooms;
	private String modeOfPayment;
	private double taxes = 7.50;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTotal_payment() {
		return price;
	}
	public void setTotal_payment(double price) {
		price = price;
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
	public void setAmount(double d) {
		// TODO Auto-generated method stub
		
	}
	
	


	
	
}
