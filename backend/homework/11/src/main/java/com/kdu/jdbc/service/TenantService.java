package com.kdu.jdbc.service;

import com.kdu.jdbc.dao.*;
import com.kdu.jdbc.dto.*;
import com.kdu.jdbc.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service class providing business logic for Tenant-related operations.
 */
@Service
@Slf4j
public class TenantService {
    private final TenantDAO tenantDAO;
    private final UsersDAO usersDAO;
    private final ShiftDAO shiftDAO;
    private final ShiftUsersDAO shiftUsersDAO;
    private final ShiftTypeDAO shiftTypeDAO;

    private final MapperService mapperService;

    /**
     * Constructor for TenantService class.
     *
     * @param tenantDAO        The TenantDAO used for Tenant-related database operations.
     * @param usersDAO         The UsersDAO used for User-related database operations.
     * @param shiftDAO         The ShiftDAO used for Shift-related database operations.
     * @param shiftUsersDAO    The ShiftUsersDAO used for ShiftUser-related database operations.
     * @param shiftTypeDAO     The ShiftTypeDAO used for ShiftType-related database operations.
     * @param mapperService    The MapperService used for mapping DTOs to entities.
     */
    public TenantService(
            TenantDAO tenantDAO,
            UsersDAO usersDAO,
            ShiftDAO shiftDAO,
            ShiftUsersDAO shiftUsersDAO,
            ShiftTypeDAO shiftTypeDAO,
            MapperService mapperService
    ) {
        this.tenantDAO = tenantDAO;
        this.usersDAO = usersDAO;
        this.shiftDAO = shiftDAO;
        this.shiftUsersDAO = shiftUsersDAO;
        this.shiftTypeDAO = shiftTypeDAO;
        this.mapperService = mapperService;
    }

    /**
     * Get a specific Tenant by ID.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Tenant.
     */
    public ResponseEntity<Tenant> getTenant(String id) {
        return new ResponseEntity<>(tenantDAO.getById(UUID.fromString(id)), HttpStatus.OK);
    }

    /**
     * Save a new Tenant.
     *
     * @param tenant The TenantDTO representing the new Tenant.
     */
    @Transactional
    public void saveTenant(TenantDTO tenant) {
        tenantDAO.save(mapperService.toEntity(tenant));
    }

    /**
     * Get ShiftUsers for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved ShiftUsers.
     */
    public ResponseEntity<List<ShiftUser>> getShiftUsers(String id) {
        return new ResponseEntity<>(shiftUsersDAO.getAllById(UUID.fromString(id)), HttpStatus.OK);
    }

    /**
     * Get ShiftTypes for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved ShiftTypes.
     */
    public ResponseEntity<List<ShiftType>> getShiftsTypes(String id) {
        return new ResponseEntity<>(shiftTypeDAO.getAllById(UUID.fromString(id)), HttpStatus.OK);
    }

    /**
     * Get Users for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Users.
     */
    public ResponseEntity<List<User>> getUsers(String id) {
        return new ResponseEntity<>(usersDAO.getAllById(UUID.fromString(id)), HttpStatus.OK);
    }

    /**
     * Get Shifts for a specific Tenant.
     *
     * @param id The ID of the Tenant.
     * @return ResponseEntity with the retrieved Shifts.
     */
    public ResponseEntity<List<Shift>> getShifts(String id) {
        return new ResponseEntity<>(shiftDAO.getAllById(UUID.fromString(id)), HttpStatus.OK);
    }

    /**
     * Update an existing User.
     *
     * @param id   The ID of the User.
     * @param user The updated User data.
     * @return ResponseEntity with a success message or not found status.
     */
    @Transactional
    public ResponseEntity<String> updateUser(String id, User user) {
        if (usersDAO.update(UUID.fromString(id), user) == 0) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else return ResponseEntity.ok("User updated successfully");
    }

    /**
     * Add a new User.
     *
     * @param userDTO The UserDTO representing the new User.
     */
    @Transactional
    public void addUser(UserDTO userDTO) {
        usersDAO.save(mapperService.toEntity(userDTO));
    }

    /**
     * Add a new Shift.
     *
     * @param shiftDTO The ShiftDTO representing the new Shift.
     */
    @Transactional
    public void addShift(ShiftDTO shiftDTO) {
        shiftDAO.save(mapperService.toEntity(shiftDTO));
    }

    /**
     * Add a new ShiftType.
     *
     * @param shiftTypeDTO The ShiftTypeDTO representing the new ShiftType.
     */
    @Transactional
    public void addShiftType(ShiftTypeDTO shiftTypeDTO) {
        shiftTypeDAO.save(mapperService.toEntity(shiftTypeDTO));
    }

    /**
     * Add a new ShiftUser.
     *
     * @param shiftUserDTO The ShiftUserDTO representing the new ShiftUser.
     */
    @Transactional
    public void addShiftUser(ShiftUserDTO shiftUserDTO) {
        shiftUsersDAO.save(mapperService.toEntity(shiftUserDTO));
    }

    /**
     * Save entities for a specific Tenant.
     *
     * @param id                        The ID of the Tenant.
     * @param tenantEntityRequestDTO   The request DTO containing entities for the Tenant.
     */
    @Transactional
    public void saveEntitiesForTenant(String id, TenantEntityRequestDTO tenantEntityRequestDTO) {
        if (tenantDAO.isPresent(UUID.fromString(id))) {
            tenantEntityRequestDTO.getUsers().forEach(usersDAO::save);
            tenantEntityRequestDTO.getShifts().forEach(shiftDAO::save);
            tenantEntityRequestDTO.getShiftUsers().forEach(shiftUsersDAO::save);
            tenantEntityRequestDTO.getShiftTypes().forEach(shiftTypeDAO::save);
        } else log.error("Tenant with id: {} not present", id);
    }
}
