package com.kdu.assesment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")
public class Inventory extends CreateUpdateInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
}
