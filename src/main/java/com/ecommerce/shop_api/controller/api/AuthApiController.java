package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.dto.auth.LoginRequest;
import com.ecommerce.shop_api.dto.auth.LoginResponse;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.UserRepository;
import com.ecommerce.shop_api.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthApiController(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
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
}