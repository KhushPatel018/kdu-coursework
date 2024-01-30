package com.kdu.jdbc.model;

import com.kdu.jdbc.constant.SHIFT_TYPE;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Builder
@Getter
public class ShiftType {
    private UUID id;
    private SHIFT_TYPE name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private String timezone;
    private UUID tenantId;
}
