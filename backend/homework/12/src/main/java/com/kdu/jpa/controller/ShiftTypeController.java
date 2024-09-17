package com.kdu.jpa.controller;

import com.kdu.jpa.entity.ShiftType;
import com.kdu.jpa.service.ShiftTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing ShiftType entities.
 */
@RestController
@RequestMapping("/api/v1/shift-type")
public class ShiftTypeController {

    private final ShiftTypeService shiftTypeService;

    /**
     * Constructs a new ShiftTypeController with the provided ShiftTypeService.
     *
     * @param shiftTypeService The ShiftTypeService to be used by the controller.
     */
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     * Handles HTTP POST requests to create a new ShiftType.
     *
     * @param shiftType The ShiftType object to be added.
     * @return ResponseEntity with a success message and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> addShift(@RequestBody ShiftType shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return new ResponseEntity<>("Shift-type created successfully", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all ShiftTypes for a specific tenant.
     *
     * @param id The UUID of the tenant.
     * @return ResponseEntity with a list of ShiftTypes and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<ShiftType>> getAllShiftTypesForTenant(@PathVariable UUID id) {
        List<ShiftType> shiftTypes = shiftTypeService.getAllShiftTypesForTenant(id);
        return ResponseEntity.ok(shiftTypes);
    }
}
