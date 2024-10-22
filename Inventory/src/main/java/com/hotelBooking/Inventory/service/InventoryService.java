package com.hotelBooking.Inventory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelBooking.Inventory.Exception.InventoryNotFoundException;
import com.hotelBooking.Inventory.Repo.InventoryRepository;
import com.hotelBooking.Inventory.model.Inventory;


@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;

	public List<Inventory> getAllItems() {
		return inventoryRepository.findAll();
	}

	public Inventory addItem(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	public Inventory updateItem(int id, Inventory inventoryDetails) {
		Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
		inventory.setItemName(inventoryDetails.getItemName());
		inventory.setQuantity(inventoryDetails.getQuantity());
		return inventoryRepository.save(inventory);
	}

	public void deleteItem(int id) {
		//inventoryRepository.deleteById(id);
		if (!inventoryRepository.existsById(id)) {
			throw new InventoryNotFoundException(id);
		}
		inventoryRepository.deleteById(id);

	}
}