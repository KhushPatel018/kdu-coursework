package com.kdu.jdbc.controller;

import com.kdu.jdbc.dto.TenantEntityRequestDTO;
import com.kdu.jdbc.model.*;
import com.kdu.jdbc.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final TenantService service;

    public UserController(TenantService service) {
        this.service = service;
    }


    @PostMapping("/tenant")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant){
        service.saveTenant(tenant);
        return new ResponseEntity<>("new tenant created with name : " + tenant.getName(), HttpStatus.CREATED);
    }

    @GetMapping("/tenant/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable String id){
        return service.getTenant(id);
    }
    @PostMapping("/tenant/{id}")
    public ResponseEntity<String> addTenantEntities(@PathVariable String id,@RequestBody TenantEntityRequestDTO tenantEntityRequestDTO)
    {
        service.saveEntitiesForTanant(id,tenantEntityRequestDTO);
        return new ResponseEntity<>("new tenant entities created", HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> getUsers(@PathVariable String id){
        return service.getUsers(id);
    }

    @GetMapping("/shifts/{id}")
    public ResponseEntity<List<Shift>> getShifts(@PathVariable String id){
        return service.getShifts(id);
    }

    @GetMapping("/shift-types/{id}")
    public ResponseEntity<List<ShiftType>> getShiftsTypes(@PathVariable String id){
        return service.getShiftsTypes(id);
    }

    @GetMapping("/shift-users/{id}")
    public ResponseEntity<List<ShiftUser>> getShiftUsers(@PathVariable String id){
        return service.getShiftUsers(id);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user){
        return service.updateUser(id,user);
    }


}
