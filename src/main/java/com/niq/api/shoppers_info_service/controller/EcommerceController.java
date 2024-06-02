package com.niq.api.shoppers_info_service.controller;

import com.niq.api.shoppers_info_service.model.ProductMetadata;
import com.niq.api.shoppers_info_service.model.ProductsByShopperEcommerce;
import com.niq.api.shoppers_info_service.model.ShopperProduct;
import com.niq.api.shoppers_info_service.service.DataService;
import com.niq.api.shoppers_info_service.service.EcommerceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api")
@RestController
@Slf4j
public class EcommerceController {

    private static final Logger logger = LoggerFactory.getLogger(EcommerceController.class);

    @Autowired
    private DataService dataService;

    @Autowired
    private EcommerceService ecommerceService;

    @PostMapping("/products/shopper/insert")
    public ResponseEntity<String> saveShopperProductList(@RequestBody ShopperProduct shopperProductList) {
        logger.info("Received request to save shopper product list for shopper {}", shopperProductList.getShopperId());
        dataService.saveShopperProductList(shopperProductList);
        return ResponseEntity.ok().body("Shopper product list saved successfully");
    }

    @PostMapping("/products/metadata/insert")
    public ResponseEntity<String> saverProductMetaData(@RequestBody ProductMetadata productMetadata) {
        logger.info("Received request to save Product metadata");
        dataService.saverProductMetaData(productMetadata);
        return ResponseEntity.ok().body("Product metadata saved successfully");
    }

    @GetMapping("/products/shopper/get")
    public List<ProductsByShopperEcommerce> getProducts(@RequestParam String shopperId, @RequestParam(required = false) String category, @RequestParam(required = false) String brand, @RequestParam(defaultValue = "10") int limit) {
        logger.info("Received request to get products for shopper {} with category {}, brand {}, and limit {}", shopperId, category, brand, limit);
        List<ProductsByShopperEcommerce> products = ecommerceService.getProductsByShopper(shopperId, category, brand, limit);
        logger.info("Returning {} products for shopper {}", products.size(), shopperId);
        return products;
    }


}
