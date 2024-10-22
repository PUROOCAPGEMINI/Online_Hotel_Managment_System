package com.hotelBooking.reservation.test;

import com.hotelBooking.reservation.exceptions.InvalidReservationException;
import com.hotelBooking.reservation.exceptions.ReservationNotFoundException;
import com.hotelBooking.reservation.exceptions.RoomUnavailableException;
import com.hotelBooking.reservation.model.Guests;
import com.hotelBooking.reservation.model.Reservation;
import com.hotelBooking.reservation.repository.ReservationRepo;
import com.hotelBooking.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepo reservationRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation = new Reservation(); // Initialize your Reservation object
        reservation.setBookingId(1L);
        reservation.setUserId(123);
        reservation.setRoomId(456L);
        reservation.setCheckin(LocalDate.now());
        reservation.setCheckout(LocalDate.now().plusDays(2));
    }

    @Test
    void testGetReservationById() {
        when(reservationRepo.findById(1L)).thenReturn(Optional.of(reservation));
        
        Reservation foundReservation = reservationService.getReservationById(1L);
        
        assertNotNull(foundReservation);
        assertEquals(reservation.getBookingId(), foundReservation.getBookingId());
        verify(reservationRepo, times(1)).findById(1L);
    }

    @Test
    void testGetReservationByIdNotFound() {
        when(reservationRepo.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ReservationNotFoundException.class, () -> reservationService.getReservationById(1L));
    }

    @Test
    void testGetReservationsByUserId() {
        when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation));
        
        List<Reservation> reservations = reservationService.getReservationsByUserId(123);
        
        assertEquals(1, reservations.size());
        assertEquals(reservation, reservations.get(0));
        verify(reservationRepo, times(1)).findAll();
    }

    @Test
    void testUpdateReservation() {
        when(reservationRepo.existsById(1L)).thenReturn(true);
        when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation);
        
        Reservation updatedReservation = new Reservation();
        updatedReservation.setUserId(123);
        updatedReservation.setRoomId(456L);
        updatedReservation.setCheckin(LocalDate.now());
        updatedReservation.setCheckout(LocalDate.now().plusDays(2));
        
        Reservation result = reservationService.updateReservation(1L, updatedReservation);
        
        assertNotNull(result);
        assertEquals(reservation.getBookingId(), result.getBookingId());
        verify(reservationRepo, times(1)).existsById(1L);
        verify(reservationRepo, times(1)).save(updatedReservation);
    }

    @Test
    void testUpdateReservationNotFound() {
        when(reservationRepo.existsById(1L)).thenReturn(false);
        
        assertThrows(ReservationNotFoundException.class, () -> reservationService.updateReservation(1L, reservation));
    }

    @Test
    void testDeleteReservation() {
        when(reservationRepo.existsById(1L)).thenReturn(true);
        doNothing().when(reservationRepo).deleteById(1L);
        
        assertDoesNotThrow(() -> reservationService.deleteReservation(1L));
        verify(reservationRepo, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReservationNotFound() {
        when(reservationRepo.existsById(1L)).thenReturn(false);
        
        assertThrows(ReservationNotFoundException.class, () -> reservationService.deleteReservation(1L));
    }

    @Test
    void testGetAllReservations() {
        when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation));
        
        List<Reservation> reservations = reservationService.getAllReservations();
        
        assertEquals(1, reservations.size());
        assertEquals(reservation, reservations.get(0));
        verify(reservationRepo, times(1)).findAll();
    }
}

