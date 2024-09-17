package com.kdu.jpa.controller;

import com.kdu.jpa.entity.Tenant;
import com.kdu.jpa.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing Tenant entities.
 */
@RestController
@RequestMapping("/api/v1/tenant")
public class TenantController {

    private final TenantService tenantService;

    /**
     * Constructs a new TenantController with the provided TenantService.
     *
     * @param tenantService The TenantService to be used by the controller.
     */
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    /**
     * Handles HTTP POST requests to create a new Tenant.
     *
     * @param tenant The Tenant object to be added.
     * @return ResponseEntity with a success message and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
        return new ResponseEntity<>("Tenant created successfully", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all Tenants.
     *
     * @return ResponseEntity with a list of Tenants and HTTP status code 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }
}
