package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.model.CartItem;
import com.ecommerce.shop_api.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "03. Cart",
        description = "Shopping cart operations"
)
@RestController
@RequestMapping("/api/v1/cart")
public class CartApiController {

    private final CartService cartService;

    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Get cart")
    public ResponseEntity<List<CartItem>> getCart(
            Authentication auth
    ) {

        return ResponseEntity.ok(
                cartService.getCart(auth.getName())
        );
    }

    @PostMapping("/{productId}")
    @Operation(summary = "Add product to cart")
    public ResponseEntity<String> add(
            @PathVariable Long productId,
            Authentication auth
    ) {

        cartService.add(
                auth.getName(),
                productId
        );

        return ResponseEntity.ok("Product added");
    }

    @PutMapping("/increase/{cartItemId}")
    @Operation(summary = "Increase quantity")
    public ResponseEntity<String> increase(
            @PathVariable Long cartItemId,
            Authentication auth
    ) {

        cartService.changeQuantity(
                auth.getName(),
                cartItemId,
                1
        );

        return ResponseEntity.ok("Quantity increased");
    }

    @PutMapping("/decrease/{cartItemId}")
    @Operation(summary = "Decrease quantity")
    public ResponseEntity<String> decrease(
            @PathVariable Long cartItemId,
            Authentication auth
    ) {

        cartService.changeQuantity(
                auth.getName(),
                cartItemId,
                -1
        );

        return ResponseEntity.ok("Quantity decreased");
    }

    @DeleteMapping("/{cartItemId}")
    @Operation(summary = "Remove item")
    public ResponseEntity<String> remove(
            @PathVariable Long cartItemId,
            Authentication auth
    ) {

        cartService.removeItem(
                auth.getName(),
                cartItemId
        );

        return ResponseEntity.ok("Removed");
    }
}