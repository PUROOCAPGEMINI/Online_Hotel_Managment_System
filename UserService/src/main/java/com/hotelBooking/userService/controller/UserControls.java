package com.hotelBooking.userService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBooking.userService.exception.ResourcesNotFoundException;
import com.hotelBooking.userService.models.AppUser;
import com.hotelBooking.userService.service.UserService;

@RestController
public class UserControls {
	
	@Autowired
	private UserService uservice;
	
	@PostMapping("/adds")
	public ResponseEntity<AppUser> createUser(@RequestBody AppUser user){
			AppUser created = uservice.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<AppUser>> getAllUsers(){
		List<AppUser> users = uservice.getAllUser();
	    if (users.isEmpty()) {
	        throw new ResourcesNotFoundException("Table is Empty");
	    }
	    return ResponseEntity.ok(users);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<AppUser> getUserById(@PathVariable int id) {
	    AppUser user = uservice.getUserById(id).orElseThrow(() -> new ResourcesNotFoundException("User not found with ID: " + id));
	    
	    return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		if(uservice.getUserById(id) != null) {
			uservice.deleteUser(id);
			System.out.println("Successfully Deleted");
		}
		else {
			throw new ResourcesNotFoundException("Resource not found with ID: "+ id);
		}
	}
	
	 
}
