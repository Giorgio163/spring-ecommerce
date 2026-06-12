package com.ecommerce.shop_api.service;

import com.ecommerce.shop_api.model.CartItem;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.model.User;
import com.ecommerce.shop_api.repository.CartItemRepository;
import com.ecommerce.shop_api.repository.ProductRepository;
import com.ecommerce.shop_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository repo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public CartService(
            CartItemRepository repo,
            UserRepository userRepo,
            ProductRepository productRepo
    ) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public List<CartItem> getCart(String email) {
        return repo.findByUserEmail(email);
    }

    public void add(String email, Long productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow();

        User user = userRepo.findByEmail(email)
                .orElseThrow();

        List<CartItem> cart = repo.findByUserEmail(email);

        for (CartItem item : cart) {

            if (item.getProductId().equals(productId)) {

                item.setQuantity(item.getQuantity() + 1);
                repo.save(item);

                return;
            }
        }

        CartItem newItem = new CartItem();
        newItem.setUser(user);
        newItem.setProductId(product.getId());
        newItem.setName(product.getName());
        newItem.setPrice(product.getPrice());
        newItem.setQuantity(1);

        repo.save(newItem);
    }

    public void clear(String email) {
        repo.deleteByUserEmail(email);
    }

    public void changeQuantity(String email, Long cartItemId, int delta) {

        CartItem item = repo.findById(cartItemId)
                .orElseThrow();

        if (!item.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        int newQty = item.getQuantity() + delta;

        if (newQty <= 0) {
            repo.delete(item);
            return;
        }

        item.setQuantity(newQty);
        repo.save(item);
    }

    public void removeItem(String email, Long cartItemId) {

        CartItem item = repo.findById(cartItemId)
                .orElseThrow();

        if (!item.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(item);
    }
}