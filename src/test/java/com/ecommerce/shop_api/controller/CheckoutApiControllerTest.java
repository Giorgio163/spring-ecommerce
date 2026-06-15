package com.ecommerce.shop_api.controller;


import com.ecommerce.shop_api.controller.api.CheckoutApiController;
import com.ecommerce.shop_api.model.Order;
import com.ecommerce.shop_api.security.jwt.JwtFilter;
import com.ecommerce.shop_api.security.jwt.JwtService;
import com.ecommerce.shop_api.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckoutApiController.class)
@AutoConfigureMockMvc(addFilters = false)
class CheckoutApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CheckoutService checkoutService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @Test
    void shouldCheckout() throws Exception {

        when(checkoutService.checkout("admin@test.com"))
                .thenReturn(new Order());

        mockMvc.perform(
                        post("/api/v1/checkout")
                                .principal(
                                        new UsernamePasswordAuthenticationToken(
                                                "admin@test.com",
                                                null
                                        )
                                )
                )
                .andExpect(status().isOk());

        verify(checkoutService).checkout("admin@test.com");
    }
}