package com.ecommerce.shop_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private Long id;
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
}