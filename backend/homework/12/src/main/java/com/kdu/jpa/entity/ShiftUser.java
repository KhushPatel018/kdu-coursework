package com.kdu.jpa.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "shift_user")
public class ShiftUser extends CreateUpdateInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shift;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tenant tenant;
}