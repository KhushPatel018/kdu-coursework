package com.kdu.jdbc.dao;

import com.kdu.jdbc.model.Shift;
import com.kdu.jdbc.util.ShiftRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private ShiftRowMapper rowMapper = new ShiftRowMapper();

    public List<Shift> getAllById(UUID id){
        return jdbcTemplate.query("select * from shifts where tenant_id = ?",rowMapper,id);
    }
    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }

    public void save(Shift shift) {
        if(shift.getId() != null && isPresent(shift.getId())){
            log.error("User Already Exist");
            return;
        }
        int row = jdbcTemplate.update("insert into users(shift_type_id,name,date_start,date_end,time_start,time_end,time_zone,tenant_id) values (?,?,?,?,?,?,?,?)",shift.getShiftTypeId(),
                shift.getName(),shift.getDateStart(),shift.getDateEnd(),shift.getTimeStart(),shift.getTimeEnd(),shift.getTimezone(),shift.getTenantId());
        log.info("rows affected : " + row);
    }
}
