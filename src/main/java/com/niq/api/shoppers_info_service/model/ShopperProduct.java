package com.niq.api.shoppers_info_service.model;

import lombok.Data;

import java.util.List;

@Data
public class ShopperProduct {

    private String shopperId;
    private List<Product> shelf;
}
