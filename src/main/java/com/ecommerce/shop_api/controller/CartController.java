package com.ecommerce.shop_api.controller;


import com.ecommerce.shop_api.mapper.CartItemMapper;
import com.ecommerce.shop_api.model.CartItem;
import com.ecommerce.shop_api.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service, CartItemMapper mapper) {
        this.service = service;
    }

    @GetMapping
    public String viewCart(Model model, Authentication auth) {

        var cart = service.getCart(auth.getName());

        double total = cart.stream()
                .mapToDouble(c -> c.getPrice() * c.getQuantity())
                .sum();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id,
                      Authentication auth) {

        service.add(auth.getName(), id);

        return "redirect:/cart";
    }

    @GetMapping("/increase/{id}")
    public String increase(@PathVariable Long id, Authentication auth) {
        service.changeQuantity(auth.getName(), id, +1);
        return "redirect:/cart";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable Long id, Authentication auth) {
        service.changeQuantity(auth.getName(), id, -1);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, Authentication auth) {
        service.removeItem(auth.getName(), id);
        return "redirect:/cart";
    }
}