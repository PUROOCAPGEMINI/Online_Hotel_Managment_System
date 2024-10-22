package com.hotelbooking.bookingService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelbooking.bookingService.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
