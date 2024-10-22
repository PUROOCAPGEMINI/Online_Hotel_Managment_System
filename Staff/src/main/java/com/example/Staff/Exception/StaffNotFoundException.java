package com.example.Staff.Exception;

public class StaffNotFoundException extends RuntimeException {
    public StaffNotFoundException(String id) {
        super("Staff not found with id: " + id);
    }
}