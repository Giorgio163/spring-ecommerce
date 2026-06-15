package com.ecommerce.shop_api.service;

import com.ecommerce.shop_api.dto.api.ProductCreateRequest;
import com.ecommerce.shop_api.exception.ProductNotFoundException;
import com.ecommerce.shop_api.model.Product;
import com.ecommerce.shop_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnAllProductsPage() {

        Product product = new Product();

        Page<Product> page =
                new PageImpl<>(List.of(product));

        when(repo.findAll(any(Pageable.class)))
                .thenReturn(page);

        Page<Product> result =
                service.getAll(PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
    }

    @Test
    void shouldFindProductById() {

        Product product = new Product();
        product.setId(1L);

        when(repo.findById(1L))
                .thenReturn(Optional.of(product));

        Product result = service.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowWhenProductNotFound() {

        when(repo.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> service.findById(99L)
        );
    }

    @Test
    void shouldCreateProduct() {

        Product product = new Product();
        product.setName("Laptop");

        when(repo.save(product))
                .thenReturn(product);

        Product result =
                service.create(product);

        assertEquals("Laptop", result.getName());
    }

    @Test
    void shouldDeleteProduct() {

        Product product = new Product();
        product.setId(1L);

        when(repo.findById(1L))
                .thenReturn(Optional.of(product));

        service.delete(1L);

        verify(repo).delete(product);
    }

    @Test
    void shouldUpdateProduct() {

        Product existing = new Product();
        existing.setId(1L);
        existing.setName("Old");

        ProductCreateRequest request =
                new ProductCreateRequest();

        request.setName("New");
        request.setPrice(BigDecimal.valueOf(99));

        when(repo.findById(1L))
                .thenReturn(Optional.of(existing));

        when(repo.save(any(Product.class)))
                .thenAnswer(i -> i.getArgument(0));

        Product updated =
                service.update(1L, request);

        assertEquals("New", updated.getName());
        assertEquals(
                BigDecimal.valueOf(99),
                updated.getPrice()
        );
    }
}