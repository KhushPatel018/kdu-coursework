package com.kdu.jdbc.service;

import com.kdu.jdbc.dao.*;
import com.kdu.jdbc.dto.TenantEntityRequestDTO;
import com.kdu.jdbc.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TenantService {
    private final TenantDAO tenantDAO;
    private final UsersDAO usersDAO;
    private final ShiftDAO shiftDAO;
    private final ShiftUsersDAO shiftUsersDAO;
    private final ShiftTypeDAO shiftTypeDAO;

    public TenantService(TenantDAO tenantDAO, UsersDAO usersDAO, ShiftDAO shiftDAO, ShiftUsersDAO shiftUsersDAO, ShiftTypeDAO shiftTypeDAO) {
        this.tenantDAO = tenantDAO;
        this.usersDAO = usersDAO;
        this.shiftDAO = shiftDAO;
        this.shiftUsersDAO = shiftUsersDAO;
        this.shiftTypeDAO = shiftTypeDAO;
    }


    public void saveTenant(Tenant tenant) {
        tenantDAO.save(tenant);
    }

    public void saveEntitiesForTanant(String id, TenantEntityRequestDTO tenantEntityRequestDTO) {
        if(tenantDAO.isPresent(UUID.fromString(id)))
        {
            tenantEntityRequestDTO.getUsers().forEach(usersDAO::save);
            tenantEntityRequestDTO.getShifts().forEach(shift -> shiftDAO.save(shift));
            tenantEntityRequestDTO.getShiftUsers().forEach(shiftUser -> shiftUsersDAO.save(shiftUser));
            tenantEntityRequestDTO.getShiftTypes().forEach(shiftType -> shiftTypeDAO.save(shiftType));
        }else log.error("tenant with id :  {} not present",id);
    }

    public ResponseEntity<List<ShiftUser>> getShiftUsers(String id) {
        return new ResponseEntity<>(shiftUsersDAO.getAllById(UUID.fromString(id)), HttpStatus.OK);
    }

    public ResponseEntity<List<ShiftType>> getShiftsTypes(String id) {
        return new ResponseEntity<>(shiftTypeDAO.getAllById(UUID.fromString(id)),HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getUsers(String id) {
        return new ResponseEntity<>(usersDAO.getAllById(UUID.fromString(id)),HttpStatus.OK);
    }

    public ResponseEntity<List<Shift>> getShifts(String id) {
        return new ResponseEntity<>(shiftDAO.getAllById(UUID.fromString(id)),HttpStatus.OK);
    }

    public ResponseEntity<String> updateUser(String id, User user) {
        if(usersDAO.update(UUID.fromString(id),user) == 0)
        {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }else return ResponseEntity.ok("User updated successfully");
    }
}
