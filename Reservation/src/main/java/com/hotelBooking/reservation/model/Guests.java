package com.hotelBooking.reservation.model;

public class Guests {
	private int guestId;
	private String name;
	private double cNumber;
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public String getName() {
		return name;
	}
	public Guests() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Guests(int guestId, String name, double cNumber, String email, String address) {
		super();
		this.guestId = guestId;
		this.name = name;
		this.cNumber = cNumber;
		this.email = email;
		this.address = address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getcNumber() {
		return cNumber;
	}
	public void setcNumber(double cNumber) {
		this.cNumber = cNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String email;
	private String address;
}
