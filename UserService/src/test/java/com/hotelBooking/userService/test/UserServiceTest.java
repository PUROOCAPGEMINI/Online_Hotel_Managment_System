package com.hotelBooking.userService.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hotelBooking.userService.Repo.UserRepo;
import com.hotelBooking.userService.models.AppUser;
import com.hotelBooking.userService.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private AppUser user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new AppUser(); // Assuming AppUser has a default constructor
        user.setId(1); // Set an ID for the user if applicable
        // Set other properties as needed
    }

    @Test
    void testCreateUser() {
        when(userRepo.save(any(AppUser.class))).thenReturn(user);
        
        AppUser createdUser = userService.createUser(user);
        
        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testGetAllUser() {
        List<AppUser> users = Arrays.asList(user);
        when(userRepo.findAll()).thenReturn(users);
        
        List<AppUser> result = userService.getAllUser();
        
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        
        Optional<AppUser> result = userService.getUserById(1);
        
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepo).deleteById(1);
        
        userService.deleteUser(1);
        
        verify(userRepo, times(1)).deleteById(1);
    }
}
