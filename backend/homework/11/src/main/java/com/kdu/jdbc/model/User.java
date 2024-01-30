package com.kdu.jdbc.model;

import lombok.*;

import java.util.UUID;
@Builder
@Getter
public class User {
    private UUID id;
    private String username;
    private int loggedin;
    private String timezone;
    private UUID tenantId;
}
