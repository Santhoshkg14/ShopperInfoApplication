package com.niq.api.shoppers_info_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shopper_products")
@Data
public class ShopperProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopperId;
    private String productId;
    private double relevancyScore;
}
