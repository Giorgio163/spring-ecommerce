package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.dto.ProductDTO;
import com.ecommerce.shop_api.mapper.ProductMapper;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String list(Model model) {

        var products = service.getAll();

        model.addAttribute("products", products);

        return "admin/products";
    }

    @GetMapping("/products/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "admin/form";
    }

    @PostMapping("/products/save")
    public String save(@ModelAttribute ProductDTO dto) {
        service.create(mapper.toEntity(dto));
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Product product = service.findById(id);

        model.addAttribute("product", product);

        return "admin/form";
    }

    @GetMapping("/products/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/admin/products";
    }
}