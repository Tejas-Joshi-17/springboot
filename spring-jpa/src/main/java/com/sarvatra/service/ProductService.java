package com.sarvatra.service;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    @Transactional
    public void checkProduct() {

        final Optional<Product> product1 = productRepository.findById(1L);
//        final Optional<Product> product2 = productRepository.findById(1L);
        final Optional<Product> product2 = productRepository.findByProductName("Coca-Cola");

        log.info(product1.get().toString());
        log.info(product2.get().toString());

        log.info("{}", product1.get() == product2.get());
    }

}
