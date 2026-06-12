package com.ecommerce.shop_api.mapper;

import com.ecommerce.shop_api.dto.CartItemDto;
import com.ecommerce.shop_api.model.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDto toDto(CartItem entity);

    CartItem toEntity(CartItemDto dto);

    List<CartItemDto> toDtoList(List<CartItem> list);
}