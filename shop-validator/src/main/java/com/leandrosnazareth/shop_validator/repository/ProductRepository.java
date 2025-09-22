package com.leandrosnazareth.shop_validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrosnazareth.shop_validator.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdentifier(String identifier);
}