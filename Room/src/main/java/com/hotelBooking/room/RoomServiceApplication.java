package com.hotelBooking.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hotelBooking.room.RoomServiceApplication;

@SpringBootApplication
public class RoomServiceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(RoomServiceApplication.class, args);
		System.out.println("Room webservice is working fine...");
	}

}
