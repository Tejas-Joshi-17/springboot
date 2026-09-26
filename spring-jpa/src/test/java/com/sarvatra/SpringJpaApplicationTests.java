package com.sarvatra;

import com.sarvatra.entities.Product;
import com.sarvatra.repository.ProductRepository;
import com.sarvatra.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class SpringJpaApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void printAllData() {
        final List<Product> allProducts = productRepository.findAll();
        log.info(allProducts.toString());
    }

    @Test
    void addProduct() {
        final Product newProduct = Product.builder()
                .productName("Pepsi")
                .productType("Cold Drink")
                .price(BigDecimal.valueOf(155L))
                .quantity(4)
                .build();
        final Product newAddedProduct = productRepository.save(newProduct);
        log.info("new added Product :- \n{}", newAddedProduct);
    }

    @Test
    void getProductByProductName() {
        final Optional<Product> pepsi = productRepository.findByProductName("Mobile Charger");
        log.info("\n{}",pepsi.isPresent() ? pepsi.get().toString() : "");
    }

    @Test
    void getProductCreatedAfter() {
        final List<Product> products = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025, 1, 1, 0, 0));
        log.info(products.toString());
    }

    @Test
    void getProductByNameAndQuantity() {
        final List<Product> productByQuantityAndPrice = productRepository.findByQuantityAndPrice(8, BigDecimal.valueOf(499.9));
        log.info(productByQuantityAndPrice.toString());
    }

    @Test
    void getProductByNameAndTitle() {
        Optional<Product> productByNameAndType = productRepository.findByProductNameAndProductType("Headphones", "electronics");
        log.info("{}", productByNameAndType.isPresent() ? productByNameAndType.get() : "");
    }

    @Test
    void checkPersistLayer() {
        productService.checkProduct();
    }

}
