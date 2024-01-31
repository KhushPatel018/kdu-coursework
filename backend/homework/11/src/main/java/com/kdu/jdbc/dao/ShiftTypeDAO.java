package com.kdu.jdbc.dao;

import com.kdu.jdbc.constant.SHIFT_TYPE;
import com.kdu.jdbc.exception.EntityNotFound;
import com.kdu.jdbc.model.ShiftType;
import com.kdu.jdbc.util.ShiftTypeRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) for handling ShiftType entities.
 */
@Repository
@Slf4j
public class ShiftTypeDAO {
    private final JdbcTemplate jdbcTemplate ;
    private ShiftTypeRowMapper rowMapper = new ShiftTypeRowMapper();

    /**
     * Constructs a ShiftTypeDAO with the specified JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used.
     */
    public ShiftTypeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of ShiftType entities by their tenant ID.
     *
     * @param id The UUID of the tenant.
     * @return A list of ShiftType entities.
     */
    public List<ShiftType> getAllById(UUID id){
        return jdbcTemplate.query("select * from shift_types where tenant_id = ?",rowMapper,id);
    }

    /**
     * Checks if a ShiftType entity with the given ID is present.
     *
     * @param id The UUID of the ShiftType.
     * @return True if the ShiftType is present, false otherwise.
     */
    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }

    /**
     * Saves a ShiftType entity. If the entity already has an ID and exists, it throws an EntityNotFound exception.
     *
     * @param shiftType The ShiftType entity to be saved.
     */
    @Transactional
    public void save(ShiftType shiftType) {
        if(shiftType.getId() != null && isPresent(shiftType.getId())){
            throw new EntityNotFound("shiftType with id " + shiftType.getId() + " Already exist");
        }
        int row = jdbcTemplate.update("insert into shift_types(uq_name,description,active,time_zone,tenant_id) values (?,?,?,?,?)",getType(shiftType.getName()),shiftType.getDescription(),shiftType.isActive(),shiftType.getTimezone(),shiftType.getTenantId());
        log.info("rows affected : " + row);
    }

    /**
     * Converts SHIFT_TYPE enum to its corresponding String representation.
     *
     * @param type The SHIFT_TYPE enum value.
     * @return String representation of the SHIFT_TYPE.
     */
    public String getType(SHIFT_TYPE type)
    {
        if(type == SHIFT_TYPE.MORNING){
            return "Morning";
        }else return type == SHIFT_TYPE.AFTERNOON ? "Afternoon" : "Evening";
    }
}
