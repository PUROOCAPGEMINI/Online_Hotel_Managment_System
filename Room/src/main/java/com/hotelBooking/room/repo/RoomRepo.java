package com.hotelBooking.room.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelBooking.room.models.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer>{

}