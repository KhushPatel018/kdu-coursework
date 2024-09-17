package com.kdu.jdbc.dao;

import com.kdu.jdbc.exception.EntityNotFound;
import com.kdu.jdbc.model.Shift;
import com.kdu.jdbc.util.ShiftRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) for handling Shift entities.
 */
@Repository
@Slf4j
public class ShiftDAO {
    private final JdbcTemplate jdbcTemplate;
    private ShiftRowMapper rowMapper = new ShiftRowMapper();

    /**
     * Constructs a ShiftDAO with the specified JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used.
     */
    public ShiftDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of Shift entities by their tenant ID.
     *
     * @param id The UUID of the tenant.
     * @return A list of Shift entities.
     */
    public List<Shift> getAllById(UUID id){
        return jdbcTemplate.query("select * from shifts where tenant_id = ?",rowMapper,id);
    }

    /**
     * Checks if a Shift entity with the given ID is present.
     *
     * @param id The UUID of the Shift.
     * @return True if the Shift is present, false otherwise.
     */
    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }

    /**
     * Saves a Shift entity. If the entity already has an ID and exists, it throws an EntityNotFound exception.
     *
     * @param shift The Shift entity to be saved.
     */
    @Transactional
    public void save(Shift shift) {
        if(shift.getId() != null && isPresent(shift.getId())){
            throw new EntityNotFound("user with id " + shift.getId() + " Already exist");
        }
        int row = jdbcTemplate.update("insert into shifts(shift_type_id,name,date_start,date_end,time_start,time_end,time_zone,tenant_id) values (?,?,?,?,?,?,?,?)",shift.getShiftTypeId(),
                shift.getName(),shift.getDateStart(),shift.getDateEnd(),shift.getTimeStart(),shift.getTimeEnd(),shift.getTimezone(),shift.getTenantId());
        log.info("rows affected : " + row);
    }
}
