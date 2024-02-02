package com.kdu.assesment.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "address")
public class Address extends CreateUpdateInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nike_name")
    String nikeName;
    String street;
    String city;
    String state;
    @Column(name = "postal_code")
    String postalCode;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
}

