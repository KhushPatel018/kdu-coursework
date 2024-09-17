package com.kdu.jdbc.util;

import com.kdu.jdbc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Slf4j
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setUsername(rs.getString("username"));
        user.setLoggedin(rs.getInt("loggedin"));
        user.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        user.setTimezone(rs.getString("time_zone"));
        return user;
    }
}
