package com.kdu.assesment.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_cart")
public class ShoppingCart extends CreateUpdateInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
