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

    //REST endpoint to save information for each of the tenant entities independently in DB.
    //● REST endpoint to save information for all entities of the tenant in one go. If any failure
    //occurs then your transaction must be rolled back.
    //● You must save all entities data using native query in DB.
    //● Endpoint to fetch data for each entity of a given tenant using (use native SQL query)
    //from DB.
    //● Endpoint to update user information.(use native SQL query)
    //● Make use of JDBC template for all above operations.
    //● For each tenant all time must be stored in DB in UTC format.
    //● For all below 4 entities you must have DDL schema on DB (Postgres/ MySql)

    @PostMapping("/tenant")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant){
        service.saveTenant(tenant);
        return new ResponseEntity<>("new tenant created with name : " + tenant.getName(), HttpStatus.CREATED);
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
