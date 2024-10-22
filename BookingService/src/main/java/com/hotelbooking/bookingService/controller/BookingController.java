package com.hotelbooking.bookingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelBooking.room.service.RoomService;
import com.hotelbooking.bookingService.exception.BookingNotFoundException;
import com.hotelbooking.bookingService.models.Booking;
import com.hotelbooking.bookingService.repo.BookingRepository;
import com.hotelbooking.bookingService.service.BookingService;

import java.time.LocalDate;


@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private  RoomService  roomService;
	
	@Autowired
	private BookingRepository bookingRepository;

	// Create a new booking
    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // Check out booking and release room
    @GetMapping("/checkout/{bookingId}")
    public void checkoutBooking(@PathVariable int bookingId) {
    	Booking booking = bookingRepository.findById(bookingId).orElseThrow(null);
    	if(booking==null)
    		throw new BookingNotFoundException("BookingID not found");
        
        // Check if the checkout date has passed
        if (booking.getCheckOutDate().isBefore(LocalDate.now())) {
            // Update room availability to true (available) since the booking has ended
            roomService.updateRoomAvailability(booking.getRoomId(), true);
        } else {
            throw new RuntimeException("Cannot checkout before the checkout date has passed.");
        }
    }
}
