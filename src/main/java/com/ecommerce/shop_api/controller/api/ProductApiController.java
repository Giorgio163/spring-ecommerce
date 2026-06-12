package com.ecommerce.shop_api.controller.api;

import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import com.ecommerce.shop_api.dto.api.ProductDto;
import com.ecommerce.shop_api.mapper.api.ProductApiMapper;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.service.ProductService;
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
    public List<ProductDto> getAll() {
        return productService.getAll()
                .stream()
                .map(ProductApiMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return ProductApiMapper.toDto(productService.findById(id));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductCreateRequest request) {
        Product product = ProductApiMapper.toEntity(request);
        return ProductApiMapper.toDto(productService.create(product));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id,
                             @RequestBody ProductCreateRequest req) {

        Product existing = productService.findById(id);

        existing.setName(req.getName());
        existing.setPrice(req.getPrice());

        Product updated = productService.create(existing);

        return ProductApiMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
