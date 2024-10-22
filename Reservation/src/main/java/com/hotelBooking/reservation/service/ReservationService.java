package com.hotelBooking.reservation.service;

// Ensure this class exists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelBooking.reservation.exceptions.InvalidReservationException;
import com.hotelBooking.reservation.exceptions.ReservationNotFoundException;
import com.hotelBooking.reservation.exceptions.RoomUnavailableException;
import com.hotelBooking.reservation.model.Guests;
import com.hotelBooking.reservation.model.Reservation;
import com.hotelBooking.reservation.repository.ReservationRepo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepo reservationRepo;

    @Autowired
    private RestTemplate restTemplate;

    private static final String ROOMS_SERVICE_URL = "http://localhost:8082/rooms"; // Update with actual service URL
    private static final String GUESTS_SERVICE_URL = "http://localhost:8083/guests"; // Update with actual service URL

    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Reservation saveReservation(Reservation reservation) throws RoomUnavailableException {
        if (!isRoomAvailable(reservation.getRoomId(), reservation.getCheckin(), reservation.getCheckout())) {
            throw new RoomUnavailableException("Room is not available for the selected dates.");
        }
        if (reservation.getUserId() == null) {
            throw new InvalidReservationException("User ID cannot be null.");
        }

        // Verify guest existence
        String guestUrl = GUESTS_SERVICE_URL + "/all";
        Guests[] guest = restTemplate.getForObject(guestUrl, Guests[].class);
        List<Guests> list=Arrays.asList(guest);
        if (guest == null) {
            throw new ReservationNotFoundException("Guest with ID " + reservation.getUserId() + " not found.");
        }

        return reservationRepo.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepo.findById(id).orElseThrow(() -> 
            new ReservationNotFoundException("Reservation with ID " + id + " not found."));
    }

    public List<Reservation> getReservationsByUserId(Integer userId) {
        return reservationRepo.findAll(); // Replace with actual filtering logic if needed
    }

    public List<Reservation> getReservationsByRoomId(Long roomId) {
        return reservationRepo.findAll(); // Replace with actual filtering logic if needed
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        if (!reservationRepo.existsById(id)) {
            throw new ReservationNotFoundException("Reservation with ID " + id + " not found.");
        }
        updatedReservation.setBookingId(id);
        return reservationRepo.save(updatedReservation);
    }

    public void deleteReservation(Long id) {
        if (!reservationRepo.existsById(id)) {
            throw new ReservationNotFoundException("Reservation with ID " + id + " not found.");
        }
        reservationRepo.deleteById(id);
    }

    private boolean isRoomAvailable(Long roomId, LocalDate checkin, LocalDate checkout) {
        // Call the Rooms service to check availability
        String availabilityUrl = ROOMS_SERVICE_URL + "/availability?roomId=" + roomId + "&checkin=" + checkin + "&checkout=" + checkout;
        return restTemplate.getForObject(availabilityUrl, Boolean.class);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll(); 
    }
}
