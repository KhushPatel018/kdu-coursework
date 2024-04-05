package com.kdu.assesment.entity;

import lombok.Data;
import jakarta.persistence.*;

import java.util.UUID;


@Data
@Entity
@Table(name = "users")
public class User extends CreateUpdateInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String role;


//    @ManyToOne(fetch = FetchType.EAGER)
//    private Address address;
}
