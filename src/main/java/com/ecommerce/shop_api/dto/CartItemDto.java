package com.ecommerce.shop_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDto {

    private Long id;
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}