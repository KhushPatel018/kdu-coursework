package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddInventoryRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for inventory-related endpoints.
 */
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    /**
     * Constructor for InventoryController.
     *
     * @param inventoryService The service for inventory-related operations.
     */
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Endpoint for adding an inventory item.
     *
     * @param addInventoryRequestDTO    The DTO containing information about the inventory item to be added.
     * @return                          ResponseEntity containing the response DTO.
     */
    @PostMapping
    public ResponseEntity<GlobalResponseDTO> addInventoryItem(@Valid @RequestBody AddInventoryRequestDTO addInventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(addInventoryRequestDTO));
    }

    /**
     * Endpoint for retrieving all inventory items.
     *
     * @return  ResponseEntity containing the response DTO.
     */
    @GetMapping
    public ResponseEntity<InventoryResponseDTO> getInventoryItems() {
        return ResponseEntity.ok(inventoryService.getInventoryItems());
    }
}
