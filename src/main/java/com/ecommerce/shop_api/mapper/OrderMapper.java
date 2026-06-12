package com.ecommerce.shop_api.mapper;


import com.ecommerce.shop_api.model.CartItem;
import com.ecommerce.shop_api.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderItem fromCart(CartItem c) {

        OrderItem i = new OrderItem();

        i.setProductId(c.getProductId());
        i.setProductName(c.getName());
        i.setPrice(c.getPrice());
        i.setQuantity(c.getQuantity());

        return i;
    }
}