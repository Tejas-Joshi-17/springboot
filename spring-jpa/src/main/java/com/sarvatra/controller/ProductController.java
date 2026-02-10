package com.sarvatra.controller;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
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

import java.util.List;

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

}
