package com.kdu.jdbc.dao;

import com.kdu.jdbc.model.ShiftType;
import com.kdu.jdbc.util.ShiftRowMapper;
import com.kdu.jdbc.util.ShiftTypeRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftTypeDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private ShiftTypeRowMapper rowMapper = new ShiftTypeRowMapper();

    public List<ShiftType> getAllById(UUID id){
        return jdbcTemplate.query("select * from shift_types where tenant_id = '?'",rowMapper,id);
    }
    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }

    public void save(ShiftType shiftType) {
        if(isPresent(shiftType.getId())){
            log.error("User Already Exist");
            return;
        }
        int row = jdbcTemplate.update("insert into shift_types(uq_name,description,active,time_zone,tenant_id) values (?,?,?,?,'?')",shiftType.getName(),shiftType.getDescription(),shiftType.isActive(),shiftType.getTimezone(),shiftType.getTenantId());
        log.info("rows affected : " + row);
    }
}
