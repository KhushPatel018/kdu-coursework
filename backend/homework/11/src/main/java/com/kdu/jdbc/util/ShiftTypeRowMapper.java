package com.kdu.jdbc.util;

import com.kdu.jdbc.constant.SHIFT_TYPE;
import com.kdu.jdbc.model.ShiftType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ShiftTypeRowMapper implements RowMapper<ShiftType> {
    @Override
    public ShiftType mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp time = rs.getTimestamp("updated_at");
        Instant updatedAt;
        if (time == null){
            updatedAt = null;
        }else updatedAt = time.toInstant();
        return ShiftType.builder()
                .id(UUID.fromString(rs.getString("id")))
                .description(rs.getString("description"))
                .active(rs.getBoolean("active"))
                .createdAt(rs.getTimestamp("created_at").toInstant())
                .updatedAt(updatedAt)
                .timezone(rs.getString("time_zone"))
                .name(getShiftType(rs.getString("uq_name")))
                .tenantId(UUID.fromString(rs.getString("tenant_id"))).build();
    }

    private SHIFT_TYPE getShiftType(String type){
        if(type.equals("Morning"))
        {
            return SHIFT_TYPE.MORNING;
        }else return (type.equals("Afternoon") ? SHIFT_TYPE.AFTERNOON : SHIFT_TYPE.EVENING);
    }
}
