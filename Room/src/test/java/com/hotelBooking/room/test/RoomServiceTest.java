package com.hotelBooking.room.test;


import com.hotelBooking.room.exception.RoomNotFoundException;
import com.hotelBooking.room.models.Room;
import com.hotelBooking.room.repo.RoomRepo;
import com.hotelBooking.room.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @Mock
    private RoomRepo roomRepository;

    @InjectMocks
    private RoomService roomService;

    private Room room;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        room = new Room(); // Initialize your Room object
        room.setId(1); // Set an ID for testing
        room.setRoomNumber("101");
        room.setType("Deluxe");
        room.setAvailable(true);
    }

    @Test
    void testGetAllRooms() {
        List<Room> roomList = Arrays.asList(room);
        when(roomRepository.findAll()).thenReturn(roomList);
        
        List<Room> result = roomService.getAllRooms();
        
        assertEquals(1, result.size());
        assertEquals(room, result.get(0));
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testAddRoom() {
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        
        Room createdRoom = roomService.addRoom(room);
        
        assertNotNull(createdRoom);
        assertEquals(room.getId(), createdRoom.getId());
        verify(roomRepository, times(1)).save(room);
    }
    
    @Test
    void testCheckRoomAvailability() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        
        boolean isAvailable = roomService.checkRoomAvailability(1);
        
        assertTrue(isAvailable);
        verify(roomRepository, times(1)).findById(1);
    }


    @Test
    void testIsRoomAvailable() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        
        boolean isAvailable = roomService.isRoomAvailable(1);
        
        assertTrue(isAvailable);
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateRoomAvailability() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        
        roomService.updateRoomAvailability(1, false);
        
        assertFalse(room.isAvailable());
        verify(roomRepository, times(1)).findById(1);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom() {
        Room updatedRoomDetails = new Room();
        updatedRoomDetails.setRoomNumber("102");
        updatedRoomDetails.setType("Suite");
        updatedRoomDetails.setAvailable(false);
        
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        
        Room updatedRoom = roomService.updateRoom(1, updatedRoomDetails);
        
        assertNotNull(updatedRoom);
        assertEquals("102", updatedRoom.getRoomNumber());
        assertEquals("Suite", updatedRoom.getType());
        assertFalse(updatedRoom.isAvailable());
        verify(roomRepository, times(1)).findById(1);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoomNotFound() {
        Room updatedRoomDetails = new Room();
        
        when(roomRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(RoomNotFoundException.class, () -> roomService.updateRoom(1, updatedRoomDetails));
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteRoom() {
        when(roomRepository.existsById(1)).thenReturn(true);
        doNothing().when(roomRepository).deleteById(1);
        
        assertDoesNotThrow(() -> roomService.deleteRoom(1));
        verify(roomRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteRoomNotFound() {
        when(roomRepository.existsById(1)).thenReturn(false);
        
        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoom(1));
        verify(roomRepository, times(1)).existsById(1);
    }
}

