package com.sarvatra;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class SpringJpaApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringJpaApplicationTests.class);

    @Autowired
    private ProductRepository productRepository;

    @Test
    void printAllData() {
        final List<Product> allProducts = productRepository.findAll();
        logger.info(allProducts.toString());
    }

    @Test
    void addProduct() {
        final Product newProduct = Product.builder()
                .sku("Tejas Joshi")
                .title("Name")
                .price(BigDecimal.valueOf(155L))
                .quantity(4)
                .build();
        final Product newAddedProduct = productRepository.save(newProduct);
        logger.info("new added Product :- \n{}", newAddedProduct);
    }

}
