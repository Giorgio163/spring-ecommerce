package com.ecommerce.shop_api.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}