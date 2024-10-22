package com.hotelBooking.userService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.hotelBooking.userService.Repo.UserRepo;
import com.hotelBooking.userService.exception.GlobalExceptionHandler;
import com.hotelBooking.userService.exception.ResourcesNotFoundException;
import com.hotelBooking.userService.exception.RoleInvalidException;
import com.hotelBooking.userService.exception.Validator;
import com.hotelBooking.userService.models.AppUser;

@Service
@RestController
public class UserService {
	
	@Autowired
	private UserRepo  urepo;
	
	public AppUser createUser(AppUser user){
	    return urepo.save(user);
	}
	
	public List<AppUser> getAllUser(){
		return urepo.findAll();
	}
	
	public Optional<AppUser> getUserById(int id) {
		return urepo.findById(id);
	}
	
	public void deleteUser(int id) {
		urepo.deleteById(id);
	}
}
