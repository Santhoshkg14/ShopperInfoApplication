package com.niq.api.shoppers_info_service.service;

import com.niq.api.shoppers_info_service.controller.EcommerceController;
import com.niq.api.shoppers_info_service.entity.ProductMetadataEntity;
import com.niq.api.shoppers_info_service.entity.ShopperProductEntity;
import com.niq.api.shoppers_info_service.model.Product;
import com.niq.api.shoppers_info_service.model.ProductsByShopperEcommerce;
import com.niq.api.shoppers_info_service.repository.ProductMetadataRepository;
import com.niq.api.shoppers_info_service.repository.ShopperProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EcommerceService {

    private static final Logger logger = LoggerFactory.getLogger(EcommerceService.class);

    @Autowired
    private ShopperProductRepository shopperProductRepository;
    @Autowired
    private ProductMetadataRepository productMetadataRepository;


    public List<ProductsByShopperEcommerce> getProductsByShopper(String shopperId, String category, String brand, int limit) {

        List<ShopperProductEntity> shopperProducts = shopperProductRepository.findByShopperId(shopperId);

        List<String> productIds = shopperProducts.stream()
                .map(ShopperProductEntity::getProductId)
                .collect(Collectors.toList());
        logger.info("List of product Ids: {} ", productIds);

        List<ProductMetadataEntity> productMetadataEntities;

        if (category != null && brand != null) {
            productMetadataEntities = productMetadataRepository.findByCategoryAndBrandAndProductIdIn(category, brand, productIds);
        } else if (category != null) {
            productMetadataEntities = productMetadataRepository.findByCategoryAndProductIdIn(category, productIds);
        } else if (brand != null) {
            productMetadataEntities = productMetadataRepository.findByBrandAndProductIdIn(brand, productIds);
        } else {
            productMetadataEntities = productMetadataRepository.findByProductIdIn(productIds);
        }


        return productMetadataEntities.stream()
                .limit(limit)
                .map(entity -> convertToProductsByShopperEcommerce(entity, shopperProducts))
                .collect(Collectors.toList());
    }

    private ProductsByShopperEcommerce convertToProductsByShopperEcommerce(ProductMetadataEntity entity, List<ShopperProductEntity> shopperProducts) {
        ProductsByShopperEcommerce product = new ProductsByShopperEcommerce();
        product.setProductId(entity.getProductId());
        product.setCategory(entity.getCategory());
        product.setBrand(entity.getBrand());

        shopperProducts.stream()
                .filter(sp -> sp.getProductId().equals(entity.getProductId()))
                .findFirst().ifPresent(shopperProduct -> product.setRelevancyScore(shopperProduct.getRelevancyScore()));

        return product;
    }


}
