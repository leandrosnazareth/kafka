package com.leandronazareth.kafka_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandronazareth.kafka_api.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
}
