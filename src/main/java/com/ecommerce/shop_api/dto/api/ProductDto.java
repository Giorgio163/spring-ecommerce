package com.ecommerce.shop_api.dto.api;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {
    public Long id;
    public String name;
    public Double price;
}