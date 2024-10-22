package com.hotelBooking.Inventory.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hotelBooking.Inventory.model.Inventory;
import com.hotelBooking.Inventory.service.InventoryService;
import jakarta.validation.Valid;


@RestController
@Validated
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/allitems")
	public List<Inventory> getAllItems() {
		return inventoryService.getAllItems();
	}

	@PostMapping("/add")
	public Inventory addItem(@Valid @RequestBody Inventory inventory) {
		return inventoryService.addItem(inventory);
	}

	@PutMapping("/update/{id}")
	public Inventory updateItem(@PathVariable int id, @Valid @RequestBody Inventory inventoryDetails) {
		return inventoryService.updateItem(id, inventoryDetails);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable int id) {
		inventoryService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}
}