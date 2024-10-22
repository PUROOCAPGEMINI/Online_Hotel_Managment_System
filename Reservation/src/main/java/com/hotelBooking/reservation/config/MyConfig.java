package com.hotelBooking.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

	@Configuration
	public class RestTemplate {
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}
}
