//package com.hotelbooking.bookingService.service;
//
//import com.hotelBooking.guestService.models.Guests;
//import com.hotelBooking.guestService.services.GuestService;
//import com.hotelBooking.room.service.RoomService;
//import com.hotelbooking.bookingService.models.Booking;
//import com.hotelbooking.bookingService.repo.BookingRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Service
//public class BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final String ROOM_SERVICE_URL = "http://localhost:8089";
//    private static final String GUEST_SERVIE_URL = "http://localhost:8084";
//    
//    @Autowired
//    private RoomService roomService;
//
////    @Autowired
////    private GuestService guestService;
//
//    public List<com.hotelbooking.bookingService.models.Booking> getAllBookings() {
//        return bookingRepository.findAll();
//    }
//
////    public Booking createBooking(Booking booking) {
////        // Check if the guest exists
////        Guests guest = guestService.getGuestById(booking.getRoomId());
////        if (guest == null) {
////            throw new RuntimeException("Guest not found.");
////        }
////
////        // Use RestTemplate to check room availability by calling RoomService
////        String url = ROOM_SERVICE_URL + "/rooms/" + booking.getRoomId() + "/availability";
////        Boolean isRoomAvailable = restTemplate.getForObject(url, Boolean.class);
////
////        if (Boolean.FALSE.equals(isRoomAvailable)) {
////            throw new RuntimeException("Room is not available for booking.");
////        }
////
////        booking.setGuest(guest.getGuestId()); // Set the guest for the booking
////        roomService.updateRoomAvailability(booking.getRoomId(), false);
////        return bookingRepository.save(booking);
////    }
////    
////    public GuestService getGuestById(int guestId) {
////    	String url = GUEST_SERVIE_URL+"/guests/"+guestId;
////    	return restTemplate.getForObject(url, GuestService.class);
//    }
//
//	
//}
