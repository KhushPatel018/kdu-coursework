package com.kdu.jpa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class TenantIdRequestDTO {
    UUID tenantId;
}
