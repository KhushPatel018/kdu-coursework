package com.kdu.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class ShiftTypeDTO {
    String name;
    String description;
    boolean active;
    String timezone;
    UUID tenantId;
}

