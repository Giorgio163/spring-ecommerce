package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.dto.auth.LoginRequest;
import com.ecommerce.shop_api.dto.auth.LoginResponse;
import com.ecommerce.shop_api.dto.auth.RegisterRequest;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.UserRepository;
import com.ecommerce.shop_api.security.jwt.JwtService;
import com.ecommerce.shop_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "01. Authentication",
        description = "JWT Authentication"
)
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthApiController(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             JwtService jwtService, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT token")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean passwordMatch = passwordEncoder.matches(
                req.getPassword(),
                user.getPassword()
        );

        if (!passwordMatch) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public ResponseEntity<String> register(
            @RequestBody @Valid RegisterRequest request
    ) {

        authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created");
    }
}