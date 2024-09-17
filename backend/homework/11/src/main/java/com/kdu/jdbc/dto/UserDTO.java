package com.kdu.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class UserDTO {
    String username;
    String timezone;
    UUID tenantId;
}
