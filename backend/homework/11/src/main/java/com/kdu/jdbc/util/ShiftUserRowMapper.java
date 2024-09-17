package com.kdu.jdbc.util;

import com.kdu.jdbc.model.ShiftUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftUserRowMapper implements RowMapper<ShiftUser> {
    @Override
    public ShiftUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ShiftUser.builder().id(UUID.fromString(rs.getString("id"))).shiftId(UUID.fromString(rs.getString("shift_id")))
                .userId(UUID.fromString(rs.getString("user_id"))).tenantId(UUID.fromString(rs.getString("tenant_id"))).build();
    }
}
