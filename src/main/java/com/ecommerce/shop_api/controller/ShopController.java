package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductService service;

    public ShopController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String products(Model model) {

        model.addAttribute("products", service.getAll());

        return "shop/products";
    }
}