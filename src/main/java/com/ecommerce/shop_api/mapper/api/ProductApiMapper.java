package com.ecommerce.shop_api.mapper.api;


import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import com.ecommerce.shop_api.dto.api.ProductDto;
import com.ecommerce.shop_api.model.Product;

public class ProductApiMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static Product toEntity(ProductCreateRequest req) {
        Product product = new Product();
        product.setName(req.getName());
        product.setPrice(req.getPrice());
        return product;
    }
}