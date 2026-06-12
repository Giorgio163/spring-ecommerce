package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import com.ecommerce.shop_api.dto.api.ProductDto;
import com.ecommerce.shop_api.mapper.api.ProductApiMapper;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> list = productService.getAll()
                .stream()
                .map(ProductApiMapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(ProductApiMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductCreateRequest req) {

        Product product = ProductApiMapper.toEntity(req);
        Product saved = productService.create(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductApiMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,
                                             @Valid @RequestBody ProductCreateRequest req) {

        Product existing = productService.findById(id);

        existing.setName(req.getName());
        existing.setPrice(req.getPrice());

        Product updated = productService.create(existing);

        return ResponseEntity.ok(ProductApiMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
