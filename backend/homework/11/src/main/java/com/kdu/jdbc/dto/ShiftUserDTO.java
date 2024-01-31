package com.kdu.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class ShiftUserDTO {
    UUID shiftId;
    UUID userId;
    UUID tenantId;
}
