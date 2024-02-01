package com.kdu.jpa.service;

import com.kdu.jpa.entity.ShiftType;
import com.kdu.jpa.exception.InvalidEntityException;
import com.kdu.jpa.exception.ItemsNotFound;
import com.kdu.jpa.repository.ShiftTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing ShiftType entities.
 */
@Service
public class ShiftTypeService {

    private final ShiftTypeRepository shiftTypeRepository;

    /**
     * Constructs a new ShiftTypeService with the provided ShiftTypeRepository.
     *
     * @param shiftTypeRepository The ShiftTypeRepository to be used by the service.
     */
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * Adds a new ShiftType to the repository.
     *
     * @param shiftType The ShiftType object to be added.
     * @throws InvalidEntityException If the shift-type is invalid or cannot be added.
     */
    public void addShiftType(ShiftType shiftType) {
        try {
            shiftTypeRepository.save(shiftType);
        } catch (Exception ex) {
            throw new InvalidEntityException("Invalid shift-type : Unable to add Cause " + ex.getMessage(), "shift-type");
        }
    }

    /**
     * Retrieves all ShiftTypes for a specific tenant.
     *
     * @param tenantId The UUID of the tenant.
     * @return List of ShiftTypes for the provided tenant.
     * @throws ItemsNotFound If shiftTypes for the provided tenant ID cannot be found.
     */
    public List<ShiftType> getAllShiftTypesForTenant(UUID tenantId) {
        List<ShiftType> shiftTypes;
        try {
            shiftTypes = shiftTypeRepository.findAllByTenantId(tenantId);
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find shiftTypes for provided tenant id with Cause : " + ex.getMessage());
        }
        return shiftTypes;
    }
}
