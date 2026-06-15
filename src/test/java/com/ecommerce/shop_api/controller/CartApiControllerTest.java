package com.ecommerce.shop_api.controller;


import com.ecommerce.shop_api.security.jwt.JwtService;
import com.ecommerce.shop_api.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CartApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @MockitoBean
    private CartService cartService;

    @Test
    void shouldReturnCart_withRealJwt() throws Exception {

        String token = jwtService.generateToken("admin@test.com", "USER");

        when(cartService.getCart("admin@test.com"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/cart")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(cartService).getCart("admin@test.com");
    }

    @Test
    void shouldAddProduct_withRealJwt() throws Exception {

        String token = jwtService.generateToken("admin@test.com", "USER");

        mockMvc.perform(post("/api/v1/cart/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(cartService).add("admin@test.com", 1L);
    }

    @Test
    void shouldRemoveProduct_withRealJwt() throws Exception {

        String token = jwtService.generateToken("admin@test.com", "USER");

        mockMvc.perform(delete("/api/v1/cart/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(cartService).removeItem("admin@test.com", 1L);
    }
}