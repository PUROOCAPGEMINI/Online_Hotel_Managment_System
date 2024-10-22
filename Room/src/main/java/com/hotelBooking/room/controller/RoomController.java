package com.hotelBooking.room.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBooking.room.models.Room;
import com.hotelBooking.room.service.RoomService;

import jakarta.validation.Valid;

@RestController 
@Validated
public class RoomController {

	@Autowired
    private RoomService roomService;

    @GetMapping("/allroom")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    
//    @GetMapping("/rooms/{roomId}/availability")
//    public boolean checkRoomAvailability(@PathVariable int roomId) {
//        return roomService.isRoomAvailable(roomId);
//    }

    @PostMapping("/add")
    public Room addRoom(@Valid @RequestBody Room room) {
    	System.out.println("Room added successfully");
        return roomService.addRoom(room);
       
    }

    @PutMapping("/{roomId}/availability")
    public void updateRoomAvailability(@PathVariable int roomId, @RequestParam boolean available) {
        roomService.updateRoomAvailability(roomId, available);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{roomId}/availability")
    public Boolean checkRoomAvailability(@PathVariable int roomId) {
    	return roomService.checkRoomAvailability(roomId); // Assuming isAvailable() method exists in Room model
    }

}

