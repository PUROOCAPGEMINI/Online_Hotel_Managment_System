package com.hotelBooking.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelBooking.reservation.exceptions.RoomUnavailableException;
import com.hotelBooking.reservation.model.Reservation;
import com.hotelBooking.reservation.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
//    public String saveReservation(@RequestBody Reservation reservation) {
        try {
            Reservation savedReservation = reservationService.saveReservation(reservation);
//            return "saved ";

            return ResponseEntity.ok(savedReservation);
        } catch (RoomUnavailableException e) {
//        	return "exception";
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/getall")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @GetMapping("/get/room/{roomId}")
    public ResponseEntity<List<Reservation>> getReservationsByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.ok(reservationService.getReservationsByRoomId(roomId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
