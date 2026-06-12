package com.ecommerce.shop_api.repository;

import com.ecommerce.shop_api.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserEmail(String email);

    void deleteByUserEmail(String email);
}