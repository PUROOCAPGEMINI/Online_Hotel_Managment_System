package com.example.Staff.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
     
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
