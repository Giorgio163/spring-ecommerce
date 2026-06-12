package com.ecommerce.shop_api.mapper;

import com.ecommerce.shop_api.dto.ProductDTO;
import com.ecommerce.shop_api.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product entity);

    default Product toEntity(ProductDTO dto) {
        Product p = new Product();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        return p;
    }
}