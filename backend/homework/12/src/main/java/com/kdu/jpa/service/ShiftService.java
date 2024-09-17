package com.kdu.jpa.service;

import com.kdu.jpa.entity.Shift;
import com.kdu.jpa.exception.InvalidEntityException;
import com.kdu.jpa.exception.ItemsNotFound;
import com.kdu.jpa.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing Shift entities.
 */
@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    /**
     * Constructs a new ShiftService with the provided ShiftRepository.
     *
     * @param shiftRepository The ShiftRepository to be used by the service.
     */
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    /**
     * Adds a new Shift to the repository.
     *
     * @param shift The Shift object to be added.
     * @throws InvalidEntityException If the shift is invalid or cannot be added.
     */
    public void addShift(Shift shift) {
        try {
            shiftRepository.save(shift);
        } catch (Exception ex) {
            throw new InvalidEntityException("Invalid shift! Can't be added with Cause : " + ex.getMessage(), "Shift");
        }
    }

    /**
     * Retrieves all Shifts for a specific tenant.
     *
     * @param tenantId The UUID of the tenant.
     * @return List of Shifts for the provided tenant.
     * @throws ItemsNotFound If shifts for the provided tenant ID cannot be found.
     */
    public List<Shift> getAllShiftsForTenant(UUID tenantId) {
        List<Shift> shiftList;
        try {
            shiftList = shiftRepository.findAllByTenantId(tenantId);
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find shifts for provided tenant id with Cause : " + ex.getMessage());
        }
        return shiftList;
    }

    /**
     * Retrieves the top 3 Shifts within a specified date range.
     *
     * @return List of the top 3 Shifts in the specified date range.
     * @throws ItemsNotFound If shifts in the provided date range cannot be found.
     */
    public List<Shift> getTop3ShiftsInDateRange() {
        List<Shift> shifts;
        LocalDate dateStart = LocalDate.of(2023, 1, 1); // 1st Jan
        LocalDate dateEnd = LocalDate.of(2023, 1, 25); // 25th Jan
        try {
            shifts = shiftRepository.findTop3ShiftsInRange(dateStart, dateEnd);
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find shifts in provided date range with Cause : " + ex.getMessage());
        }
        return shifts;
    }
}
