package com.kdu.jdbc.dao;

import com.kdu.jdbc.model.User;
import com.kdu.jdbc.util.TenantRowMapper;
import com.kdu.jdbc.util.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class UsersDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private UserRowMapper rowMapper = new UserRowMapper();

    public List<User> getAllById(UUID id){
       return jdbcTemplate.query("select * from users where tenant_id = ?",rowMapper,id);
    }

    public int update(UUID id,User user){
        return jdbcTemplate.update("update users set username = ? , loggedin = ? , time_zone = ? where id = ?",user.getUsername(),user.getLoggedin(),user.getTimezone(),id);
    }

    private boolean isPresent(UUID id){
        return !getAllById(id).isEmpty();
    }
    public void save(User user) {
        if(user.getId() != null && isPresent(user.getId())){
            log.error("User Already Exist");
            return;
        }
        int row = jdbcTemplate.update("insert into users(username,loggedin,time_zone,tenant_id) values (?,?,?,?)",user.getUsername(),user.getLoggedin(),user.getTimezone(),user.getTenantId());
        log.info("rows affected : " + row);
    }
}
