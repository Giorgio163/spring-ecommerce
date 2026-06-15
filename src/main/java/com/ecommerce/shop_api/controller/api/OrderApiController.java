package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "05. Orders",
        description = "Customer orders"
)
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<List<Order>> getOrders() {

        return ResponseEntity.ok(
                orderService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                orderService.findById(id)
        );
    }
}