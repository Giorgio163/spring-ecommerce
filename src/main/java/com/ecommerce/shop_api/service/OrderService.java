package com.ecommerce.shop_api.service;

import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Order not found")
                );
    }
}