package com.leandrosnazareth.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrosnazareth.shop_api.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
}
