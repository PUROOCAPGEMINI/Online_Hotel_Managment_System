package com.hotelBooking.Inventory.test;


import com.hotelBooking.Inventory.Exception.InventoryNotFoundException;
import com.hotelBooking.Inventory.Repo.InventoryRepository;
import com.hotelBooking.Inventory.model.Inventory;
import com.hotelBooking.Inventory.service.InventoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory(); // Initialize your Inventory object
        inventory.setId(1); // Set an ID for testing
        inventory.setItemName("Test Item");
        inventory.setQuantity(10);
    }

    @Test
    void testGetAllItems() {
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);
        
        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        List<Inventory> result = inventoryService.getAllItems();

        assertEquals(1, result.size());
        assertEquals("Test Item", result.get(0).getItemName());
        verify(inventoryRepository, times(1)).findAll();
    }

    @Test
    void testAddItem() {
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        Inventory result = inventoryService.addItem(inventory);

        assertNotNull(result);
        assertEquals("Test Item", result.getItemName());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void testUpdateItem() {
        Inventory updatedInventory = new Inventory();
        updatedInventory.setItemName("Updated Item");
        updatedInventory.setQuantity(20);

        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(updatedInventory);

        Inventory result = inventoryService.updateItem(1, updatedInventory);

        assertNotNull(result);
        assertEquals("Updated Item", result.getItemName());
        verify(inventoryRepository, times(1)).findById(1);
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    void testUpdateItemNotFound() {
        Inventory updatedInventory = new Inventory();

        when(inventoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> inventoryService.updateItem(1, updatedInventory));
        verify(inventoryRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteItem() {
        when(inventoryRepository.existsById(1)).thenReturn(true);

        inventoryService.deleteItem(1);

        verify(inventoryRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteItemNotFound() {
        when(inventoryRepository.existsById(1)).thenReturn(false);

        assertThrows(InventoryNotFoundException.class, () -> inventoryService.deleteItem(1));
        verify(inventoryRepository, never()).deleteById(1);
    }
}
