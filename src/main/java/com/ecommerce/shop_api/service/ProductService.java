package com.ecommerce.shop_api.service;


import com.ecommerce.shop_api.exception.ProductNotFoundException;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public Page<Product> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product update(Long id, ProductCreateRequest req) {

        Product product = findById(id);

        product.setName(req.getName());
        product.setPrice(req.getPrice());

        return repo.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        repo.delete(product);
    }
}