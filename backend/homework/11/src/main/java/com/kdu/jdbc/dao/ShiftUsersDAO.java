package com.kdu.jdbc.dao;

import com.kdu.jdbc.exception.EntityNotFound;
import com.kdu.jdbc.model.ShiftUser;
import com.kdu.jdbc.util.ShiftUserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) for handling ShiftUser entities.
 */
@Repository
@Slf4j
public class ShiftUsersDAO {
    private final JdbcTemplate jdbcTemplate;
    private ShiftUserRowMapper rowMapper = new ShiftUserRowMapper();

    /**
     * Constructs a ShiftUsersDAO with the specified JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used.
     */
    public ShiftUsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of ShiftUser entities by their tenant ID.
     *
     * @param id The UUID of the tenant.
     * @return A list of ShiftUser entities.
     */
    public List<ShiftUser> getAllById(UUID id){
        return jdbcTemplate.query("select * from shift_user where tenant_id = ?",rowMapper,id);
    }

    /**
     * Checks if a ShiftUser entity with the given ID is present.
     *
     * @param id The UUID of the ShiftUser.
     * @return True if the ShiftUser is present, false otherwise.
     */
    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }

    /**
     * Saves a ShiftUser entity. If the entity already has an ID and exists, it throws an EntityNotFound exception.
     *
     * @param shiftUser The ShiftUser entity to be saved.
     */
    @Transactional
    public void save(ShiftUser shiftUser) {
        if(shiftUser.getId() != null && isPresent(shiftUser.getId())){
            throw new EntityNotFound("shiftUser with id " + shiftUser.getId() + " Already exist");
        }
        int row = jdbcTemplate.update("insert into shift_user(shift_id,user_id,tenant_id) values (?,?,?)",shiftUser.getShiftId(),shiftUser.getUserId(),shiftUser.getTenantId());
        log.info("rows affected : " + row);
    }
}
