package com.ecommerce.shop_api.controller;


import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.OrderRepository;
import com.ecommerce.shop_api.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    public OrderController(OrderRepository orderRepo,
                           UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String list(Model model, Authentication auth) {

        User user = userRepo.findByEmail(auth.getName())
                .orElseThrow();

        List<Order> orders = orderRepo.findByUser(user);

        model.addAttribute("orders", orders);

        return "orders";
    }
}