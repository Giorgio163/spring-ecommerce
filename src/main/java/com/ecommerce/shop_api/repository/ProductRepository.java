package com.ecommerce.shop_api.repository;

import com.ecommerce.shop_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}