package com.hotelBooking.guestService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hotelBooking.guestService.Repo.GuestsRepository;
import com.hotelBooking.guestService.exceptions.ResourceNotFoundException;
import com.hotelBooking.guestService.models.Guests;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

	@Autowired
	private GuestsRepository guestsRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String ROOM_SERVICE_URL = "http://localhost:8085/rooms";

	// Add a new guest
	public Guests addGuest(Guests guest) {
		// Check if a guest with the same ID already exists
		if (guestsRepository.existsById(guest.getGuestId())) {
			throw new ResourceNotFoundException("Guest with ID " + guest.getGuestId() + " already exists");
		}
		return guestsRepository.save(guest);
	}

	// Get all guests
	public List<Guests> getAllGuests() {
		return guestsRepository.findAll();
	}

	// Get guest by ID
	public Guests getGuestById(int id) {
		Optional<Guests> guest = guestsRepository.findById(id);
		if (!guest.isPresent()) {
			throw new ResourceNotFoundException("Guest not found with Id : " + id);
		}
		return guest.get();
	}

	// Update guest by ID
	public Guests updateGuest(int id, Guests guest) {
		Guests existingGuest = (Guests) guestsRepository.findById(id).orElse(null);
		if (existingGuest == null) {
			throw new ResourceNotFoundException("Guest with ID " + id + " not found");
		}

		existingGuest.setName(existingGuest.getName());
		existingGuest.setcNumber(existingGuest.getcNumber());
		existingGuest.setEmail(existingGuest.getEmail());
		existingGuest.setAddress(existingGuest.getAddress());
		return guestsRepository.save(existingGuest);
	}

	public void deleteGuestById(int id) {
		if (guestsRepository.existsById(id)) {
			guestsRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Guest with ID " + id + " not found");
		}

	}

	// Delete all guests
	public void deleteAllGuests() {
		guestsRepository.deleteAll();
	}

	public Guests addGuestAndBookRoom(Guests guest, int roomId) {
		// Check room availability from RoomService
		Boolean isRoomAvailable = checkRoomAvailability(roomId);
		if (!isRoomAvailable) {
			throw new ResourceNotFoundException("Room with ID " + roomId + " is not available");
		}

		// If room is available, save guest and proceed with booking
		if (guestsRepository.existsById(guest.getGuestId())) {
			throw new ResourceNotFoundException("Guest with ID " + guest.getGuestId() + " already exists");
		}

		// Proceed with saving the guest (and assume booking is successful)
		return guestsRepository.save(guest);
	}

	// Check Room Availability by making REST call to RoomService
	private Boolean checkRoomAvailability(int roomId) {
		String availabilityUrl = ROOM_SERVICE_URL + "/" + roomId + "/availability";
		Boolean response = restTemplate.getForObject(availabilityUrl, Boolean.class);
		return response != null && response;
	}
}
