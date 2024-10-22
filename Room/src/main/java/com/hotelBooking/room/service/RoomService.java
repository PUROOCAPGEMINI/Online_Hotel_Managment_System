package com.hotelBooking.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelBooking.room.exception.RoomNotFoundException;
import com.hotelBooking.room.models.Room;
import com.hotelBooking.room.repo.RoomRepo;



@Service
public class RoomService {

	@Autowired
    private RoomRepo roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }
    
    public boolean checkRoomAvailability(int roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(null);
        if(room==null)
        	throw new RoomNotFoundException("Room not found with id: " + roomId);
        return room.isAvailable();
    }

    
    public boolean isRoomAvailable(int roomId) {
        return roomRepository.findById(roomId).map(Room::isAvailable).orElse(false);
    }
    
    public void updateRoomAvailability(int roomId, boolean availability) {
        Room room = roomRepository.findById(roomId).orElseThrow(null);
        if(room==null)
        	throw new RoomNotFoundException(roomId);
        
        room.setAvailable(availability);
        roomRepository.save(room);
    }

    public Room updateRoom(int id, Room roomDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setType(roomDetails.getType());
        room.setAvailable(roomDetails.isAvailable());
        return roomRepository.save(room);
    }

    public void deleteRoom(int id) {
       // roomRepository.deleteById(id);
    	if (!roomRepository.existsById(id)) {
            throw new RoomNotFoundException(id);
        }
        roomRepository.deleteById(id);
    }
}
