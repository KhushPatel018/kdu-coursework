package com.kdu.jpa.controller;

import com.kdu.jpa.entity.ShiftUser;
import com.kdu.jpa.service.ShiftUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing ShiftUser entities.
 */
@RestController
@RequestMapping("/shift-user")
public class ShiftUserController {

    /**
     * Service class for ShiftUser operations.
     */
    private final ShiftUserService shiftUserService;

    /**
     * Constructs a new ShiftUserController with the provided ShiftUserService.
     *
     * @param shiftUserService The ShiftUserService to be used by the controller.
     */
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     * Handles HTTP POST requests to add a new ShiftUser.
     *
     * @param shiftUser The ShiftUser object to be added.
     * @return ResponseEntity with a success message and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return new ResponseEntity<>("Shift-user added successfully", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all ShiftUsers for a specific tenant.
     *
     * @param id The UUID of the tenant.
     * @return ResponseEntity with a list of ShiftUsers and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<ShiftUser>> getAllShiftUsersForTenant(@PathVariable UUID id) {
        List<ShiftUser> shiftUsers = shiftUserService.getAllShiftUsersForTenant(id);
        return ResponseEntity.ok(shiftUsers);
    }

    /**
     * Handles HTTP DELETE requests to delete a ShiftUser by ID.
     *
     * @param id The UUID of the ShiftUser to be deleted.
     * @return ResponseEntity with a success message and HTTP status code 200 (OK).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID id) {
        shiftUserService.deleteShiftUser(id);
        return new ResponseEntity<>("Shift-user deleted successfully", HttpStatus.OK);
    }
}
