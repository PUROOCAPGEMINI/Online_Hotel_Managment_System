package com.hotelBooking.Inventory.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
public class Inventory {

    @Id
    private int id;

    @NotBlank(message = "Item name must not be empty")
    private String itemName;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be at least 0")
    private int quantity;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
