package com.hotelBooking.guestService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelBooking.guestService.models.Guests;

public interface GuestsRepository extends JpaRepository<Guests, Integer>{


}
