package com.hotelBooking.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelBooking.reservation.model.Reservation;


public interface ReservationRepo extends JpaRepository<Reservation, Long>{

	
}
