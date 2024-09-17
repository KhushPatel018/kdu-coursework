package com.kdu.jpa.service;

import com.kdu.jpa.entity.Shift;
import com.kdu.jpa.entity.ShiftUser;
import com.kdu.jpa.exception.EntityNotExist;
import com.kdu.jpa.exception.InvalidEntityException;
import com.kdu.jpa.exception.ItemsNotFound;
import com.kdu.jpa.repository.ShiftUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing ShiftUser entities.
 */
@Service
public class ShiftUserService {
    private final ShiftUserRepository shiftUserRepository;

    /**
     * Constructs a new ShiftUserService with the provided ShiftUserRepository.
     *
     * @param shiftUserRepository The ShiftUserRepository to be used by the service.
     */
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    /**
     * Adds a new ShiftUser to the repository.
     *
     * @param shiftUser The ShiftUser object to be added.
     * @throws InvalidEntityException If the shift-user is invalid or cannot be added.
     */
    public void addShiftUser(ShiftUser shiftUser) {
        try {
            shiftUserRepository.save(shiftUser);
        } catch (Exception ex) {
            throw new InvalidEntityException("Invalid shiftUser : Unable to add Cause " + ex.getMessage(), "shift-user");
        }
    }

    /**
     * Retrieves all ShiftUsers for a specific tenant.
     *
     * @param tenantId The UUID of the tenant.
     * @return List of ShiftUsers for the provided tenant.
     * @throws ItemsNotFound If shiftUsers for the provided tenant ID cannot be found.
     */
    public List<ShiftUser> getAllShiftUsersForTenant(UUID tenantId) {
        List<ShiftUser> shiftUsers;
        try {
            shiftUsers = shiftUserRepository.findAllByTenantId(tenantId);
        } catch (Exception ex) {
            throw new ItemsNotFound("Not able to find shiftUsers for provided tenant id with Cause : " + ex.getMessage());
        }
        return shiftUsers;
    }

    /**
     * Deletes a ShiftUser by ID, but only if the associated Shift finishes at 23:00 UTC.
     *
     * @param shiftUserId The UUID of the ShiftUser to be deleted.
     * @throws EntityNotExist If the associated Shift is not finishing at 23:00 UTC.
     * @throws ItemsNotFound If the ShiftUser for the provided shiftUserId cannot be found.
     */
    public void deleteShiftUser(UUID shiftUserId) {
        Optional<ShiftUser> isShiftUser = shiftUserRepository.findById(shiftUserId);
        if (isShiftUser.isPresent()) {
            ShiftUser shiftUser = isShiftUser.get();
            Shift shift = shiftUser.getShift();
            LocalTime time = LocalTime.of(23, 0, 0);
            if (shift != null && shift.getTimeEnd().toLocalTime().equals(time)) {
                shiftUserRepository.delete(shiftUser);
            } else {
                throw new EntityNotExist("Shift is not finishing at 23:00 UTC", "Shift");
            }
        } else {
            throw new ItemsNotFound("Not able to find shiftUser for provided shiftUserId");
        }
    }
}
