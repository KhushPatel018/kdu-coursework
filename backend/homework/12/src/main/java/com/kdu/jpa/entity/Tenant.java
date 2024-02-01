package com.kdu.jpa.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tenants")
public class Tenant extends CreateUpdateInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(name = "time_zone")
    private String timeZone;
}