package com.hotelBooking.Inventory.Exception;

@SuppressWarnings("serial")
public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(int id) {
        super("Inventory item not found with id: " + id);
    }
}