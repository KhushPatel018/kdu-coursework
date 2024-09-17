package com.kdu.jdbc.model;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@NoArgsConstructor
@ToString
public class User {
    private UUID id;
    private String username;
    private int loggedin;
    private String timezone;
    private UUID tenantId;

}
