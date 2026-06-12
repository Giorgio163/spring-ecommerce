package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import com.ecommerce.shop_api.dto.api.ProductDto;
import com.ecommerce.shop_api.mapper.api.ProductApiMapper;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products API", description = "CRUD operations for products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> list = productService.getAll()
                .stream()
                .map(ProductApiMapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(ProductApiMapper.toDto(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductCreateRequest req) {

        Product product = ProductApiMapper.toEntity(req);
        Product saved = productService.create(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductApiMapper.toDto(saved));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,
                                             @Valid @RequestBody ProductCreateRequest req) {

        Product existing = productService.findById(id);

        existing.setName(req.getName());
        existing.setPrice(req.getPrice());

        Product updated = productService.create(existing);

        return ResponseEntity.ok(ProductApiMapper.toDto(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
