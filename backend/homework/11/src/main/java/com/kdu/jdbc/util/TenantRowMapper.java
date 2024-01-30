package com.kdu.jdbc.util;

import com.kdu.jdbc.model.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;


public class TenantRowMapper implements RowMapper<Tenant> {
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tenant.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .createdBy(rs.getString("created_at"))
                .createdAt(rs.getTimestamp("created_at").toInstant())
                .updatedAt(rs.getTimestamp("updated_at").toInstant())
                .updatedBy(rs.getString("updated_by"))
                .build();
    }
}
