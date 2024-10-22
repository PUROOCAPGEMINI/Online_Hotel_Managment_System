package com.hotelBooking.guestService.test;


import com.hotelBooking.guestService.Repo.GuestsRepository;
import com.hotelBooking.guestService.exceptions.ResourceNotFoundException;
import com.hotelBooking.guestService.models.Guests;
import com.hotelBooking.guestService.services.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GuestServiceTest {

    @Mock
    private GuestsRepository guestsRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GuestService guestService;

    private Guests guest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guest = new Guests(); // Initialize your Guests object
        guest.setGuestId(1); // Set an ID for testing
        guest.setName("John Doe");
        guest.setcNumber("1234567890");
        guest.setEmail("john.doe@example.com");
        guest.setAddress("123 Main St");
    }

    @Test
    void testAddGuest() {
        when(guestsRepository.existsById(1)).thenReturn(false);
        when(guestsRepository.save(any(Guests.class))).thenReturn(guest);

        Guests result = guestService.addGuest(guest);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(guestsRepository, times(1)).save(guest);
    }

    @Test
    void testAddGuestAlreadyExists() {
        when(guestsRepository.existsById(1)).thenReturn(true);

        assertThrows(ResourceNotFoundException.class, () -> guestService.addGuest(guest));
    }

    @Test
    void testGetAllGuests() {
        List<Guests> guestList = new ArrayList<>();
        guestList.add(guest);
        
        when(guestsRepository.findAll()).thenReturn(guestList);

        List<Guests> result = guestService.getAllGuests();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(guestsRepository, times(1)).findAll();
    }

    @Test
    void testGetGuestById() {
        when(guestsRepository.findById(1)).thenReturn(Optional.of(guest));

        Guests result = guestService.getGuestById(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(guestsRepository, times(1)).findById(1);
    }

    @Test
    void testGetGuestByIdNotFound() {
        when(guestsRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.getGuestById(1));
    }

    @Test
    void testUpdateGuest() {
        Guests updatedGuest = new Guests();
        updatedGuest.setName("Jane Doe");
        updatedGuest.setcNumber("0987654321");
        updatedGuest.setEmail("jane.doe@example.com");
        updatedGuest.setAddress("456 Elm St");

        when(guestsRepository.findById(1)).thenReturn(Optional.of(guest));
        when(guestsRepository.save(any(Guests.class))).thenReturn(updatedGuest);

        Guests result = guestService.updateGuest(1, updatedGuest);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(guestsRepository, times(1)).findById(1);
        verify(guestsRepository, times(1)).save(any(Guests.class));
    }

    @Test
    void testUpdateGuestNotFound() {
        when(guestsRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> guestService.updateGuest(1, guest));
    }

    @Test
    void testDeleteGuestById() {
        when(guestsRepository.existsById(1)).thenReturn(true);

        guestService.deleteGuestById(1);

        verify(guestsRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteGuestByIdNotFound() {
        when(guestsRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> guestService.deleteGuestById(1));
        verify(guestsRepository, never()).deleteById(1);
    }

    @Test
    void testDeleteAllGuests() {
        guestService.deleteAllGuests();
        verify(guestsRepository, times(1)).deleteAll();
    }

    @Test
    void testAddGuestAndBookRoomRoomUnavailable() {
        when(guestsRepository.existsById(1)).thenReturn(false);
        when(restTemplate.getForObject(any(String.class), eq(Boolean.class))).thenReturn(false); // Simulate room not available

        assertThrows(ResourceNotFoundException.class, () -> guestService.addGuestAndBookRoom(guest, 1));
    }

    @Test
    void testAddGuestAndBookRoomSuccess() {
        when(guestsRepository.existsById(1)).thenReturn(false);
        when(restTemplate.getForObject(any(String.class), eq(Boolean.class))).thenReturn(true); // Simulate room available
        when(guestsRepository.save(any(Guests.class))).thenReturn(guest);

        Guests result = guestService.addGuestAndBookRoom(guest, 1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(guestsRepository, times(1)).save(guest);
    }
}
