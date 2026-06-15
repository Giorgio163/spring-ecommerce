package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.controller.api.ProductApiController;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.security.SecurityConfig;
import com.ecommerce.shop_api.security.jwt.JwtFilter;
import com.ecommerce.shop_api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductApiController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private SecurityConfig securityConfig;

    @Test
    @WithMockUser
    void shouldReturnProducts() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(999));

        Page<Product> page = new PageImpl<>(List.of(product));

        when(productService.getAll(any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnSingleProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productService.findById(anyLong()))
                .thenReturn(product);

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteProduct() throws Exception {

        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());

        verify(productService).delete(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanCreateProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        when(productService.create(any()))
                .thenReturn(product);

        mockMvc.perform(
                        post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                    {
                        "name":"Laptop",
                        "price":1200
                    }
                    """)
                )
                .andExpect(status().isCreated());
    }
}