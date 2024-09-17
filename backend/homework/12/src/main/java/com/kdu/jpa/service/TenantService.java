package com.kdu.jpa.service;

import com.kdu.jpa.entity.Tenant;
import com.kdu.jpa.exception.InvalidEntityException;
import com.kdu.jpa.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Tenant entities.
 */
@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    /**
     * Constructs a new TenantService with the provided TenantRepository.
     *
     * @param tenantRepository The TenantRepository to be used by the service.
     */
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Adds a new Tenant to the repository.
     *
     * @param tenant The Tenant object to be added.
     * @throws InvalidEntityException If the tenant is invalid or cannot be added.
     */
    public void addTenant(Tenant tenant) {
        try {
            tenantRepository.save(tenant);
        } catch (Exception ex) {
            throw new InvalidEntityException("Invalid Tenant : Unable to add with Cause " + ex.getMessage(), "shift-user");
        }
    }

    /**
     * Retrieves all Tenants.
     *
     * @return List of all Tenants.
     */
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
