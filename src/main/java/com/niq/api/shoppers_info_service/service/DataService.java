package com.niq.api.shoppers_info_service.service;

import com.niq.api.shoppers_info_service.controller.EcommerceController;
import com.niq.api.shoppers_info_service.entity.ProductMetadataEntity;
import com.niq.api.shoppers_info_service.entity.ShopperProductEntity;
import com.niq.api.shoppers_info_service.exception.ProductAlreadyExistsException;
import com.niq.api.shoppers_info_service.exception.ShopperAlreadyExistsException;
import com.niq.api.shoppers_info_service.model.ProductMetadata;
import com.niq.api.shoppers_info_service.model.ShopperProduct;
import com.niq.api.shoppers_info_service.repository.ProductMetadataRepository;
import com.niq.api.shoppers_info_service.repository.ShopperProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private ShopperProductRepository shopperProductRepository;

    @Autowired
    private ProductMetadataRepository productMetadataRepository;

    public void saveShopperProductList(ShopperProduct shopperProduct) {
        String shopperId = shopperProduct.getShopperId();

        boolean exists = shopperProductRepository.existsByShopperId(shopperId);

        if (exists) {
            logger.warn("ShopperId {} already exists in the database, skipping insertion", shopperId);
            throw new ShopperAlreadyExistsException("Shopper ID " + shopperId + " already exists.");
        }

        shopperProduct.getShelf().forEach(product -> {
            ShopperProductEntity entity = new ShopperProductEntity();
            entity.setShopperId(shopperId);
            entity.setProductId(product.getProductId());
            entity.setRelevancyScore(product.getRelevancyScore());
            shopperProductRepository.save(entity);
        });
        logger.info("Completed saving shopper product list for shopper {}", shopperId);
    }

    public void saverProductMetaData(ProductMetadata productMetadata) {
        String productId = productMetadata.getProductId();

        boolean exists = productMetadataRepository.existsByProductId(productId);

        if (exists) {
            logger.warn("ProductId {} already exists in the database, skipping insertion", productId);
            throw new ProductAlreadyExistsException("ProductId ID " + productId + " already exists.");
        }

        ProductMetadataEntity entity = new ProductMetadataEntity();
        entity.setProductId(productMetadata.getProductId());
        entity.setCategory(productMetadata.getCategory());
        entity.setBrand(productMetadata.getBrand());
        productMetadataRepository.save(entity);

        logger.info("Completed saving product meta data for {}", productId);
    }
}
