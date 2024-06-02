package com.niq.api.shoppers_info_service.model;

import lombok.Data;

@Data
public class ProductsByShopperEcommerce {

    private String productId;
    private String category;
    private String brand;
    private double relevancyScore;
}
