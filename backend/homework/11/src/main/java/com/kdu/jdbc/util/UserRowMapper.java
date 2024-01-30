package com.kdu.jdbc.util;

import com.kdu.jdbc.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder().id(UUID.fromString(rs.getString("id")))
                .username(rs.getString("username"))
                .tenantId(UUID.fromString(rs.getString("tenant_id")))
                .timezone(rs.getString("time_zone"))
                .loggedin(rs.getInt("loggedin"))
                .build();
    }
}
