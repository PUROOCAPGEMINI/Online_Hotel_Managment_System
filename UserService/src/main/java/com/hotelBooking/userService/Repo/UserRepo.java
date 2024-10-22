package com.hotelBooking.userService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.hotelBooking.userService.models.AppUser;

@Repository
//@EnableJpaRepositories(basePackages = "com.hotel.userservice.Repo")
public interface UserRepo extends JpaRepository<AppUser, Integer>{
//	User findByUsername(String username);
}
