package com.kdu.assesment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product extends CreateUpdateInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    String description;
    double price;
    Long quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    Inventory inventory;
    @ManyToOne(fetch = FetchType.EAGER)
    ShoppingCart cart;
}
