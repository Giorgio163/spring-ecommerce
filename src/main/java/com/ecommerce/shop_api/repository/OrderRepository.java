package com.ecommerce.shop_api.repository;

import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}