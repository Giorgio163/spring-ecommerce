package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.controller.api.OrderApiController;
import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.security.jwt.JwtFilter;
import com.ecommerce.shop_api.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderApiControllerTest {

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldGetOrders() throws Exception {

        when(orderService.findAll())
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/api/v1/orders")
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetOrder() throws Exception {

        Order order = new Order();

        when(orderService.findById(1L))
                .thenReturn(order);

        mockMvc.perform(
                        get("/api/v1/orders/1")
                )
                .andExpect(status().isOk());
    }
}
