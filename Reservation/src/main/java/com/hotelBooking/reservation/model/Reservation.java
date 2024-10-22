package com.hotelBooking.reservation.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reservations")
public class Reservation {

    
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private Long bookingId;

    @NotNull(message = "Hotel ID cannot be null")
    private Long hotelId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotNull(message = "Check-in date cannot be null")
    @FutureOrPresent(message = "Check-in date must be today or in the future")
    private LocalDate checkin;

    @NotNull(message = "Check-out date cannot be null")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkout;

    @Min(value = 1, message = "Number of guests must be at least 1")
    private int numOfGuests;

    @DecimalMin(value = "0.0", inclusive = false, message = "Final price must be greater than 0")
    private double finalPrice;
	
	
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	public int getNumOfGuests() {
		return numOfGuests;
	}
	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public Reservation(Long bookingId, Long hotelId, Long roomId, Integer userId, LocalDate checkin, LocalDate checkout,
			int numOfGuests, double finalPrice) {
		super();
		this.bookingId = bookingId;
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.userId = userId;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.finalPrice = finalPrice;
	}
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

}
