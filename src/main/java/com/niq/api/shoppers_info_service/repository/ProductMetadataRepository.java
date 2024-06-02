package com.niq.api.shoppers_info_service.repository;


import com.niq.api.shoppers_info_service.entity.ProductMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadataEntity, String> {

    boolean existsByProductId(String productId);

    List<ProductMetadataEntity> findByCategoryAndBrandAndProductIdIn(String category, String brand, List<String> productIds);

    List<ProductMetadataEntity> findByCategoryAndProductIdIn(String category, List<String> productIds);

    List<ProductMetadataEntity> findByBrandAndProductIdIn(String brand, List<String> productIds);

    List<ProductMetadataEntity> findByProductIdIn(List<String> productIds);
}
