package com.hotelBooking.staff.test;


import com.example.Staff.Exception.StaffNotFoundException;
import com.example.Staff.Model.Staff;
import com.example.Staff.Repository.StaffRepository;
import com.example.Staff.Services.StaffService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService;

    private Staff staff;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        staff = new Staff(); // Initialize your Staff object
        staff.setNic("1"); // Set an NIC for testing
        staff.setEmpName("Abhishek");
        staff.setOccupation("Manager");
    }

    @Test
    void testGetAllStaff() {
        List<Staff> staffList = Arrays.asList(staff);
        when(staffRepository.findAll()).thenReturn(staffList);
        
        List<Staff> result = staffService.getAllStaff();
        
        assertEquals(1, result.size());
        assertEquals(staff, result.get(0));
        verify(staffRepository, times(1)).findAll();
    }

    @Test
    void testAddStaff() {
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
        
        Staff createdStaff = staffService.addStaff(staff);
        
        assertNotNull(createdStaff);
        assertEquals(staff.getNic(), createdStaff.getNic());
        verify(staffRepository, times(1)).save(staff);
    }

    @Test
    void testUpdateStaff() {
        when(staffRepository.findById("1")).thenReturn(Optional.of(staff));
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
        
        Staff updatedStaff = new Staff();
        updatedStaff.setEmpName("Abhishek");
        updatedStaff.setOccupation("Senior Manager");
        
        Staff result = staffService.updateStaff("1", updatedStaff);
        
        assertNotNull(result);
        assertEquals("Abhishek", result.getEmpName());
        assertEquals("Senior Manager", result.getOccupation());
        verify(staffRepository, times(1)).findById("1");
        verify(staffRepository, times(1)).save(staff);
    }

    @Test
    void testDeleteStaff() {
        when(staffRepository.existsById("1")).thenReturn(true);
        doNothing().when(staffRepository).deleteById("1");
        
        assertDoesNotThrow(() -> staffService.deleteStaff("1"));
        
        verify(staffRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteStaffNotFound() {
        when(staffRepository.existsById("1")).thenReturn(false);
        
        assertThrows(StaffNotFoundException.class, () -> staffService.deleteStaff("1"));
        
        verify(staffRepository, times(1)).existsById("1");
    }
}
