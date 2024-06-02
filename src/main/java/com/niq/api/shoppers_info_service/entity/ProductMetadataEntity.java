package com.niq.api.shoppers_info_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_metadata")
@Data
public class ProductMetadataEntity {
    @Id
    private String productId;
    private String category;
    private String brand;

}
