package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddInventoryRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponseDTO> addInventoryItem(@Valid @RequestBody AddInventoryRequestDTO addInventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(addInventoryRequestDTO));
    }

    @GetMapping
    public ResponseEntity<InventoryResponseDTO> getInventoryItems() {
        return ResponseEntity.ok(inventoryService.getInventoryItems());
    }
}
