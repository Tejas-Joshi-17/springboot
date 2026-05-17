package com.sarvatra.service;

import com.sarvatra.dto.OrderRequestDto;
import com.sarvatra.dto.OrderRequestItemDto;
import com.sarvatra.dto.ProductDto;
import com.sarvatra.entity.Product;
import com.sarvatra.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        List<ProductDto> products = new ArrayList<>();
        for (Product product : inventories) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .build();
            products.add(productDto);
        }

        return products;
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with id :- {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return ProductDto.builder()
                .id(inventory.get().getId())
                .name(inventory.get().getName())
                .price(inventory.get().getPrice())
                .stock(inventory.get().getStock())
                .build();
    }

    @Transactional
    public Double getItemPrice(Long id) {
        log.info("Fetching Product data with id :- {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.get().getPrice();
    }

}
