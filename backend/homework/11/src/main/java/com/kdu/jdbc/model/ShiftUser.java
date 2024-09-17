package com.kdu.jdbc.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ShiftUser {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
}
