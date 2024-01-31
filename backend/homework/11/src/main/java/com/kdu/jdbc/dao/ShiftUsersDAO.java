package com.kdu.jdbc.dao;

import com.kdu.jdbc.model.ShiftUser;
import com.kdu.jdbc.model.User;
import com.kdu.jdbc.util.ShiftUserRowMapper;
import com.kdu.jdbc.util.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftUsersDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private ShiftUserRowMapper rowMapper = new ShiftUserRowMapper();

    public List<ShiftUser> getAllById(UUID id){
        return jdbcTemplate.query("select * from shift_user where tenant_id = ?",rowMapper,id);
    }

    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }


    public void save(ShiftUser shiftUser) {
        if(shiftUser.getId() != null && isPresent(shiftUser.getId())){
            log.error("User Already Exist");
            return;
        }
        int row = jdbcTemplate.update("insert into shift_types(shift_id,user_id,tenant_id) values (?,?,?)",shiftUser.getShiftId(),shiftUser.getUserId(),shiftUser.getTenantId());
        log.info("rows affected : " + row);
    }
}
