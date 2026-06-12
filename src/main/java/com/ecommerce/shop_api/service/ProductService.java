package com.ecommerce.shop_api.service;


import com.ecommerce.shop_api.exception.ProductNotFoundException;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}