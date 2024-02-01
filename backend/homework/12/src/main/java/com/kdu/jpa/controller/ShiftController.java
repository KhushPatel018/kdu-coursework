package com.kdu.jpa.controller;

import com.kdu.jpa.entity.Shift;
import com.kdu.jpa.service.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing Shift entities.
 */
@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    private final ShiftService shiftService;

    /**
     * Constructs a new ShiftController with the provided ShiftService.
     *
     * @param shiftService The ShiftService to be used by the controller.
     */
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * Handles HTTP POST requests to create a new Shift.
     *
     * @param shift The Shift object to be added.
     * @return ResponseEntity with a success message and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> addShift(@RequestBody Shift shift) {
        shiftService.addShift(shift);
        return new ResponseEntity<>("Shift created successfully", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all Shifts for a specific tenant.
     *
     * @param id The UUID of the tenant.
     * @return ResponseEntity with a list of Shifts and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Shift>> getAllShiftsForTenant(@PathVariable UUID id) {
        List<Shift> shifts = shiftService.getAllShiftsForTenant(id);
        return ResponseEntity.ok(shifts);
    }

    /**
     * Handles HTTP GET requests to retrieve the top 3 Shifts in a specified date range.
     *
     * @return ResponseEntity with a list of top 3 Shifts and HTTP status code 200 (OK).
     */
    @GetMapping("/top3")
    public ResponseEntity<List<Shift>> getTop3ShiftsInDateRange() {
        List<Shift> shifts = shiftService.getTop3ShiftsInDateRange();
        return ResponseEntity.ok(shifts);
    }
}
