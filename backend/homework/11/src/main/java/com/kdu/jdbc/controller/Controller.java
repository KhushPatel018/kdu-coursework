package com.kdu.jdbc.controller;

import com.kdu.jdbc.dto.*;
import com.kdu.jdbc.model.*;
import com.kdu.jdbc.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing Tenants, Users, Shifts, ShiftTypes, and ShiftUsers.
 */
@RestController
@RequestMapping("/api/v1")
public class Controller {

    private final TenantService service;

    /**
     * Constructor for Controller class.
     *
     * @param service The TenantService used for handling business logic.
     */
    public Controller(TenantService service) {
        this.service = service;
    }

    static final String SUCCESS = "Added Successfully";

    /**
     * Add a new Tenant.
     *
     * @param tenant The TenantDTO representing the new Tenant.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/tenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenant) {
        service.saveTenant(tenant);
        return new ResponseEntity<>("New tenant created with name: " + tenant.getName(), HttpStatus.CREATED);
    }

    /**
     * Get a specific Tenant by ID.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Tenant.
     */
    @GetMapping("/tenant/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable String id) {
        return service.getTenant(id);
    }

    /**
     * Add entities for a specific Tenant.
     *
     * @param id                      The ID of the Tenant.
     * @param tenantEntityRequestDTO The request DTO containing entities for the Tenant.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/tenant/{id}")
    public ResponseEntity<String> addTenantEntities(@PathVariable String id, @RequestBody TenantEntityRequestDTO tenantEntityRequestDTO) {
        service.saveEntitiesForTenant(id, tenantEntityRequestDTO);
        return new ResponseEntity<>("New tenant entities created", HttpStatus.CREATED);
    }

    /**
     * Add a new User.
     *
     * @param userDTO The UserDTO representing the new User.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        service.addUser(userDTO);
        return new ResponseEntity<>("User: " + userDTO.getUsername() + SUCCESS, HttpStatus.CREATED);
    }

    /**
     * Get Users for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Users.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> getUsers(@PathVariable String id) {
        return service.getUsers(id);
    }

    /**
     * Update an existing User.
     *
     * @param id   The ID of the User.
     * @param user The updated User data.
     * @return ResponseEntity with a success message.
     */
    @PutMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    /**
     * Get Shifts for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Shifts.
     */
    @GetMapping("/shifts/{id}")
    public ResponseEntity<List<Shift>> getShifts(@PathVariable String id) {
        return service.getShifts(id);
    }

    /**
     * Add a new Shift.
     *
     * @param shiftDTO The ShiftDTO representing the new Shift.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/shifts")
    public ResponseEntity<String> addShift(@RequestBody ShiftDTO shiftDTO) {
        service.addShift(shiftDTO);
        return new ResponseEntity<>("Shift: " + shiftDTO.getName() + SUCCESS, HttpStatus.CREATED);
    }

    /**
     * Get ShiftTypes for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved ShiftTypes.
     */
    @GetMapping("/shift-types/{id}")
    public ResponseEntity<List<ShiftType>> getShiftTypes(@PathVariable String id) {
        return service.getShiftsTypes(id);
    }

    /**
     * Add a new ShiftType.
     *
     * @param shiftTypeDTO The ShiftTypeDTO representing the new ShiftType.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/shift-types")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftTypeDTO) {
        service.addShiftType(shiftTypeDTO);
        return new ResponseEntity<>("ShiftType: " + shiftTypeDTO.getName() + SUCCESS, HttpStatus.CREATED);
    }

    /**
     * Get ShiftUsers for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved ShiftUsers.
     */
    @GetMapping("/shift-users/{id}")
    public ResponseEntity<List<ShiftUser>> getShiftUsers(@PathVariable String id) {
        return service.getShiftUsers(id);
    }

    /**
     * Add a new ShiftUser.
     *
     * @param shiftUserDTO The ShiftUserDTO representing the new ShiftUser.
     * @return ResponseEntity with a success message.
     */
    @PostMapping("/shift-users")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO shiftUserDTO) {
        service.addShiftUser(shiftUserDTO);
        return new ResponseEntity<>(
                "ShiftUser uid: " + shiftUserDTO.getUserId() + " sid: " + shiftUserDTO.getShiftId() + SUCCESS,
                HttpStatus.CREATED
        );
    }
}
