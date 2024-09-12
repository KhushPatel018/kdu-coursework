package com.kdu.springmvc.controllers;

import com.kdu.springmvc.constants.LoggingSystem;
import com.kdu.springmvc.dtos.request.VehicleRequestDTO;
import com.kdu.springmvc.dtos.response.VehicleResponseDTO;
import com.kdu.springmvc.exceptions.VehicleAlreadyExist;
import com.kdu.springmvc.exceptions.VehicleNotFoundException;
import com.kdu.springmvc.services.VehicleService;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleResponseDTO>> findAll() {
        List<VehicleResponseDTO> vehicleResponseDTOS = vehicleService.getAll();
        return new ResponseEntity<>(vehicleResponseDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        try {
            vehicleService.create(vehicleRequestDTO);
            return ResponseEntity.ok("Vehicle created successfully with id " + vehicleRequestDTO.getId());
        } catch (VehicleAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Integer id) {
        ResponseEntity<String> response;
        try {
            LoggingSystem.logInfo(vehicleService.findById(id).toString());
            response = new ResponseEntity<>(vehicleService.findById(id).toString(), HttpStatus.OK);
        } catch (VehicleNotFoundException e) {
            LoggingSystem.logError(e.toString());
            response = new ResponseEntity<>(vehicleService.getNullObj().toString(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        try {
            vehicleService.update(id, vehicleRequestDTO);
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
        return ResponseEntity.ok("Vehicle updated successfully with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            vehicleService.delete(id);
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
        return new ResponseEntity<>("New User Deleted Successfully with id : " + id, HttpStatus.OK);
    }

    @GetMapping("/getMax")
    public ResponseEntity<String> getMaximumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMaxPricedVehicle().toString(), HttpStatus.OK);
    }

    @GetMapping("/getMin")
    public ResponseEntity<String> getMinimumPricedVehicle() {
        return new ResponseEntity<>(vehicleService.getMinPricedVehicle().toString(), HttpStatus.OK);
    }
}
