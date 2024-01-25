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

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> findAll() {
        List<VehicleResponseDTO> vehicleResponseDTOS = vehicleService.getAll();
        return new ResponseEntity<>(vehicleResponseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
            vehicleService.create(vehicleRequestDTO);
            return ResponseEntity.ok("Vehicle created successfully with id " + vehicleRequestDTO.getId());
    }


    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO)  {
        vehicleService.update(id, vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle updated successfully with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        vehicleService.delete(id);
        return new ResponseEntity<>("New User Deleted Successfully with id : " + id, HttpStatus.OK);
    }

    @GetMapping("/max-vehicle")
    public ResponseEntity<VehicleResponseDTO> getMaximumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMaxPricedVehicle(), HttpStatus.OK);
    }

    @GetMapping("/min-vehicle")
    public ResponseEntity<VehicleResponseDTO> getMinimumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMinPricedVehicle(), HttpStatus.OK);
    }
}
