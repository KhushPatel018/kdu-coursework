package com.kdu.assesment.controller;

import com.kdu.assesment.entity.Address;
import com.kdu.assesment.entity.Inventory;
import com.kdu.assesment.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
@PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Inventory inventory){
        inventoryService.addInventory(inventory);
        return ResponseEntity.ok("Inventory created by " + inventory.getCreatedBy());
    }
    @GetMapping
    public ResponseEntity<List<Inventory>> getAll(){
        return ResponseEntity.ok(inventoryService.getAll());
    }
}
