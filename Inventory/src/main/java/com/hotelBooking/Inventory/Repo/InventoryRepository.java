package com.hotelBooking.Inventory.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelBooking.Inventory.model.Inventory;

public interface InventoryRepository  extends JpaRepository<Inventory, Integer>{

}
