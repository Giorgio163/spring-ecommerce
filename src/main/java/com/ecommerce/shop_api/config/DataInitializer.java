package com.ecommerce.shop_api.config;

import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.ProductRepository;
import com.ecommerce.shop_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
                    create("iPhone 15", BigDecimal.valueOf(999)),
                    create("MacBook Pro", BigDecimal.valueOf(2499)),
                    create("AirPods Pro", BigDecimal.valueOf(249)),
                    create("iPad Air", BigDecimal.valueOf(799)),
                    create("Nike Shoes", BigDecimal.valueOf(120)),
                    create("T-Shirt", BigDecimal.valueOf(25)),
                    create("Gaming Chair", BigDecimal.valueOf(350)),
                    create("4K Monitor", BigDecimal.valueOf(450)),
                    create("Mechanical Keyboard", BigDecimal.valueOf(99)),
                    create("Gaming Mouse", BigDecimal.valueOf(49)),
                    create("iPhone 18", BigDecimal.valueOf(999)),
                    create("MacBook Pro M4", BigDecimal.valueOf(2499)),
                    create("AirPods Pro V2", BigDecimal.valueOf(249)),
                    create("iPad Air 256GB", BigDecimal.valueOf(799)),
                    create("Nike Shoes Yellow", BigDecimal.valueOf(120)),
                    create("T-Shirt white", BigDecimal.valueOf(25)),
                    create("Gaming Chair professional", BigDecimal.valueOf(350)),
                    create("4K Monitor used", BigDecimal.valueOf(450)),
                    create("Mechanical Keyboard new", BigDecimal.valueOf(99)),
                    create("Gaming Mouse blue", BigDecimal.valueOf(499))
            );

            productRepo.saveAll(products);
        }
    }

    private Product create(String name, BigDecimal price) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        return p;
    }
}