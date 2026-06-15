package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.service.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "04. Checkout",
        description = "Order creation"
)
@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutApiController {

    private final CheckoutService checkoutService;

    public CheckoutApiController(
            CheckoutService checkoutService
    ) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    @Operation(summary = "Checkout cart")
    public ResponseEntity<Order> checkout(
            Authentication auth
    ) {

        Order order =
                checkoutService.checkout(
                        auth.getName()
                );

        return ResponseEntity.ok(order);
    }
}