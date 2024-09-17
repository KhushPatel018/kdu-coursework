package com.kdu.jdbc.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class Tenant {
    private UUID id;
    private String name;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
