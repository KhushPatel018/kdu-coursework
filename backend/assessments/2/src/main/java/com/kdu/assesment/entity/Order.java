package com.kdu.assesment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "order")
public class Order extends CreateUpdateInformation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    LocalDate date;
    double amount;
    @OneToOne(fetch = FetchType.EAGER)
    ShoppingCart cart;
}
