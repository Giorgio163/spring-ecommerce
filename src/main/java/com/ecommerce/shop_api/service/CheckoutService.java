package com.ecommerce.shop_api.service;

import com.ecommerce.shop_api.model.*;
import com.ecommerce.shop_api.repository.CartItemRepository;
import com.ecommerce.shop_api.repository.OrderRepository;
import com.ecommerce.shop_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CheckoutService {

    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    public CheckoutService(CartItemRepository cartRepo,
                           OrderRepository orderRepo,
                           UserRepository userRepo) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Order checkout(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow();

        List<CartItem> cart = cartRepo.findByUserEmail(email);

        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);

        double total = 0;

        for (CartItem c : cart) {

            OrderItem item = new OrderItem();
            item.setProductId(c.getProductId());
            item.setProductName(c.getName());
            item.setPrice(c.getPrice());
            item.setQuantity(c.getQuantity());

            order.addItem(item);

            total += c.getPrice() * c.getQuantity();
        }

        order.setTotal(total);

        Order savedOrder = orderRepo.save(order);

        cartRepo.deleteByUserEmail(email);

        return savedOrder;
    }
}