package com.hotelBooking.bookingService.test;


import com.hotelBooking.guestService.services.GuestService;
import com.hotelBooking.room.service.RoomService;
import com.hotelbooking.bookingService.models.Booking;
import com.hotelbooking.bookingService.repo.BookingRepository;
import com.hotelbooking.bookingService.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RoomService roomService;

    @Mock
    private GuestService guestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateBooking() {
        // Arrange
        Booking booking = new Booking();
        booking.setRoomId(1); // Set room ID

        when(guestService.getGuestById(any(Integer.class))).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(booking);
        });
        assertEquals("Guest not found.", exception);
    }
  
}

