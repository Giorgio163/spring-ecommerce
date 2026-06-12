package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.service.CartService;
import com.ecommerce.shop_api.service.CheckoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService service;
    private final CartService cartService;

    public CheckoutController(CheckoutService service,
                              CartService cartService) {
        this.service = service;
        this.cartService = cartService;
    }

    @PostMapping
    public String checkout(Model model, Authentication auth) {

        model.addAttribute("cart",
                cartService.getCart(auth.getName()));

        return "checkout";
    }

    @PostMapping("/pay")
    public String pay(Authentication auth) {

        service.checkout(auth.getName());

        return "redirect:/orders";
    }
}