package com.ecommerce.shop_api.config;

import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.ProductRepository;
import com.ecommerce.shop_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(
            UserRepository userRepo,
            ProductRepository productRepo,
            PasswordEncoder encoder
    ) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        initUsers();
        initProducts();
    }

    private void initUsers() {

        if (userRepo.count() == 0) {

            User admin = new User();
            admin.setEmail("admin@test.com");
            admin.setPassword(encoder.encode("admin"));
            admin.setRole("ADMIN");

            userRepo.save(admin);

            User user = new User();
            user.setEmail("user@test.com");
            user.setPassword(encoder.encode("user"));
            user.setRole("USER");

            userRepo.save(user);
        }
    }

    private void initProducts() {

        if (productRepo.count() == 0) {

            List<Product> products = List.of(
                    create("iPhone 15", 999),
                    create("MacBook Pro", 2499),
                    create("AirPods Pro", 249),
                    create("iPad Air", 799),
                    create("Nike Shoes", 120),
                    create("T-Shirt", 25),
                    create("Gaming Chair", 350),
                    create("4K Monitor", 450),
                    create("Mechanical Keyboard", 99),
                    create("Gaming Mouse", 49)
            );

            productRepo.saveAll(products);
        }
    }

    private Product create(String name, double price) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        return p;
    }
}