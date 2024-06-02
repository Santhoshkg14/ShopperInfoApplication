package com.niq.api.shoppers_info_service.repository;

import com.niq.api.shoppers_info_service.entity.ShopperProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopperProductRepository extends JpaRepository<ShopperProductEntity, String> {

    boolean existsByShopperId(String shopperId);

    List<ShopperProductEntity> findByShopperId(String shopperId);
}
