package com.sarvatra.service;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void checkProduct() {

        final Optional<Product> product1 = productRepository.findById(1L);
        final Optional<Product> product2 = productRepository.findById(1L);
        
        logger.info(product1.get().toString());
        logger.info(product2.get().toString());

        logger.info("{}", product1.get() == product2.get());
    }

}
