package com.kdu.jdbc.util;

import com.kdu.jdbc.model.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ShiftRowMapper implements RowMapper<Shift> {

    @Override
    public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp time = rs.getTimestamp("updated_at");
        Instant updatedAt;
        if (time == null){
            updatedAt = null;
        }else updatedAt = time.toInstant();
        return Shift.builder()
                .id(UUID.fromString(rs.getString("id")))
                .shiftTypeId(UUID.fromString(rs.getString("shift_type_id")))
                .name(rs.getString("name"))
                .dateStart((rs.getDate("date_start")))
                .dateEnd(rs.getDate("date_end"))
                .timeStart(rs.getTime("time_start"))
                .timeEnd(rs.getTime("time_end"))
                .createdAt(rs.getTimestamp("created_at").toInstant())
                .updatedAt(updatedAt)
                .timezone(rs.getString("time_zone"))
                .tenantId(UUID.fromString(rs.getString("tenant_id")))
                .build();
    }
}
