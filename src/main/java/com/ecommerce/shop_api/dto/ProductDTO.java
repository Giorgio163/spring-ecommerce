package com.ecommerce.shop_api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;

}