package com.kdu.springmvc.controller;

import com.kdu.springmvc.dto.request.VehicleRequestDTO;
import com.kdu.springmvc.dto.response.VehicleResponseDTO;
import com.kdu.springmvc.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling CRUD operations on vehicles through RESTful APIs.
 * All endpoints are relative to the base path "/api/vehicles".
 */
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * Constructor for VehicleController, injecting the VehicleService dependency.
     *
     * @param vehicleService An instance of VehicleService for handling business logic.
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Retrieves all vehicles.
     *
     * @return ResponseEntity containing a list of VehicleResponseDTOs with HTTP status OK (200).
     */
    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> findAll() {
        List<VehicleResponseDTO> vehicleResponseDTOS = vehicleService.getAll();
        return new ResponseEntity<>(vehicleResponseDTOS, HttpStatus.OK);
    }

    /**
     * Creates a new vehicle.
     *
     * @param vehicleRequestDTO The request body containing information for creating a new vehicle.
     * @return ResponseEntity with a success message and HTTP status OK (200).
     */
    @PostMapping
    public ResponseEntity<String> createVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        vehicleService.create(vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle created successfully with id " + vehicleRequestDTO.getId());
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return ResponseEntity containing the VehicleResponseDTO with HTTP status OK (200).
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
    }

    /**
     * Updates an existing vehicle by its ID.
     *
     * @param id                 The ID of the vehicle to update.
     * @param vehicleRequestDTO The request body containing updated information for the vehicle.
     * @return ResponseEntity with a success message and HTTP status OK (200).
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        vehicleService.update(id, vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle updated successfully with id " + id);
    }

    /**
     * Deletes a vehicle by its ID.
     *
     * @param id The ID of the vehicle to delete.
     * @return ResponseEntity with a success message and HTTP status OK (200).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        vehicleService.delete(id);
        return new ResponseEntity<>("Vehicle deleted successfully with id: " + id, HttpStatus.OK);
    }

    /**
     * Retrieves the vehicle with the maximum price.
     *
     * @return ResponseEntity containing the VehicleResponseDTO with HTTP status OK (200).
     */
    @GetMapping("/max-vehicle")
    public ResponseEntity<VehicleResponseDTO> getMaximumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMaxPricedVehicle(), HttpStatus.OK);
    }

    /**
     * Retrieves the vehicle with the minimum price.
     *
     * @return ResponseEntity containing the VehicleResponseDTO with HTTP status OK (200).
     */
    @GetMapping("/min-vehicle")
    public ResponseEntity<VehicleResponseDTO> getMinimumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMinPricedVehicle(), HttpStatus.OK);
    }
}
