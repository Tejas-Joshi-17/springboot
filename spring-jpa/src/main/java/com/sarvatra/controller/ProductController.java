package com.sarvatra.controller;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        final List<Product> allOrderByQuantityDesc = productRepository.findAllByOrderByQuantityDesc();
        return new ResponseEntity<>(allOrderByQuantityDesc, HttpStatus.OK);
    }

    @GetMapping(path = "/sort-by-parameter")
    public ResponseEntity<List<Product>> getProductsSortByCriteria(@RequestParam(defaultValue = "id") String criteria) {
        return new ResponseEntity<>(productRepository.findAll(Sort.by(Sort.Direction.DESC, criteria)), HttpStatus.OK);
    }

    @GetMapping(path = "/get-stationary")
    public ResponseEntity<List<Product>> getStationary() {
        return new ResponseEntity<>(productRepository.findByProductType("stationery", Sort.by("price")), HttpStatus.OK);
    }

    @GetMapping(path = "/check-status")
    public ResponseEntity<Product> checkStatus() {
        final Optional<Product> product1 = productRepository.findById(1L);
        final Optional<Product> product2 = productRepository.findById(1L);
        final Optional<Product> product3 = productRepository.findById(1L);
        final Optional<Product> product4 = productRepository.findById(1L);
        log.info("{}", product1.get() == product2.get());
        return new ResponseEntity<>(product1.get(), HttpStatus.valueOf(200));
    }

    @Transactional
    @GetMapping(path = "/check-transactional-annotation")
    public ResponseEntity<String> checkTransactionalAnnotation() {
        Product product = Product.builder()
                .price(BigDecimal.valueOf(45L))
                .quantity(4)
                .createdAt(LocalDateTime.of(2025, 4, 5, 5, 9, 5))
                .updatedAt(LocalDateTime.of(2025, 4, 5, 5, 9, 5))
                .productName("test product")
                .productType("sex toy")
                .build();


        productRepository.save(product);
        throw new RuntimeException("Failing");

//        return new ResponseEntity<>("Ok", HttpStatus.valueOf(200));
    }
}
