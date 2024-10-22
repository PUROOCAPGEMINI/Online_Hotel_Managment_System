package com.hotelBooking.guestService.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hotelBooking.guestService.models.Guests;
import com.hotelBooking.guestService.services.GuestService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@Validated
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    // Add a new guest
    @PostMapping("/add")
    public Guests addGuest(@Valid @RequestBody Guests guest) {
        return guestService.addGuest(guest);
    }

    // Get all guests
    @GetMapping("/all")
    public List<Guests> getAllGuests() {
        return guestService.getAllGuests();
    }

    // Get guest by ID
    @GetMapping("/{id}")
    public Guests getGuestById(@PathVariable int id) {
        return guestService.getGuestById(id);
    }

    // Update guest by ID
    @PutMapping("/update/{id}")
    public Guests updateGuestById(@Valid @PathVariable int id, @RequestBody Guests guest) {
        return guestService.updateGuest(id, guest);
    }

    // Delete guest by ID
    @DeleteMapping("/delete/{id}")
    public void deleteGuestById(@PathVariable int id) {
        guestService.deleteGuestById(id);
    }

    // Delete all guests
    @DeleteMapping("/deleteAll")
    public void deleteAllGuests(){
        guestService.deleteAllGuests();
    }
    
    // Add a new guest and book room
    @PostMapping("/add-and-book")
    public Guests addGuestAndBookRoom(@Valid @RequestBody Guests guest, @RequestParam int roomId) {
        return guestService.addGuestAndBookRoom(guest, roomId);
    }
}
