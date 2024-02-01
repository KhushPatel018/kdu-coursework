package com.kdu.jpa.entity;


import lombok.Data;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User extends CreateUpdateInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    @Column(name = "logged_in")
    private int loggedIn;

    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tenant tenant;
}
