package com.hotelBooking.guestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuestSerivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestSerivceApplication.class, args);
		System.out.println("Guest Management is working fine...");
	}

}
