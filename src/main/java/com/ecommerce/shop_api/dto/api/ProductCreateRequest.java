package com.ecommerce.shop_api.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {

    private String name;
    private Double price;
}
