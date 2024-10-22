package com.hotelBooking.guestService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
public class Guests {
	@Id
	@Min(value = 1, message = "Guest ID must be greater than 0")
	private int guestId;
	
	@NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
//	@NotEmpty(message = "Contact number is required")
//	@Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
	private String cNumber;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
	private String address;
	
	
	public Guests() {}
	
	
	public Guests(int guestId, String name, String cNumber, String email, String address) {
		super();
		this.guestId = guestId;
		this.name = name;
		this.cNumber = cNumber;
		this.email = email;
		this.address = address;
	}


	public int getGuestId() {
		return guestId;
	}


	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getcNumber() {
		return cNumber;
	}


	public void setcNumber(String cNumber) {
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
	
	
	
}
