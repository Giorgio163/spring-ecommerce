package com.ecommerce.shop_api.controller;

import com.ecommerce.shop_api.controller.api.AuthApiController;
import com.ecommerce.shop_api.dto.auth.RegisterRequest;
import com.ecommerce.shop_api.repository.UserRepository;
import com.ecommerce.shop_api.security.jwt.JwtService;
import com.ecommerce.shop_api.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void registerShouldReturnOk() throws Exception {

        doNothing().when(authService).register(any(RegisterRequest.class));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "email":"test@test.com",
                          "password":"1234"
                        }
                        """))
                .andExpect(status().isCreated());
    }
}
