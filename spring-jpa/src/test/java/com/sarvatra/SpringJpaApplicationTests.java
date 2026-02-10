package com.sarvatra;

import com.sarvatra.entities.Product;
import com.sarvatra.interfac.ProductInterface;
import com.sarvatra.interfac.ProductProjectionClass;
import com.sarvatra.repository.ProductRepository;
import com.sarvatra.service.ProductService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringJpaApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringJpaApplicationTests.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void printAllData() {
        final List<Product> allProducts = productRepository.findAll();
        logger.info(allProducts.toString());
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
        logger.info("new added Product :- \n{}", newAddedProduct);
    }

    @Test
    void getProductByProductName() {
        final Optional<Product> pepsi = productRepository.findByProductName("Mobile Charger");
        logger.info("\n{}",pepsi.isPresent() ? pepsi.get().toString() : "");
    }

    @Test
    void getProductCreatedAfter() {
        final List<Product> products = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025, 1, 1, 0, 0));
        logger.info(products.toString());
    }

    @Test
    void getProductByNameAndQuantity() {
        final List<Product> productByQuantityAndPrice = productRepository.findByQuantityAndPrice(8, BigDecimal.valueOf(499.9));
        logger.info(productByQuantityAndPrice.toString());
    }

    @Test
    void getProductByNameAndTitle() {
        Optional<Product> productByNameAndType = productRepository.findByProductNameAndProductType("Headphones", "electronics");
        logger.info("{}", productByNameAndType.isPresent() ? productByNameAndType.get() : "");
    }

    @Test
    void getGroupOfProducts() {
        final List<ProductInterface> productsByType = productRepository.findByProductType("electronics");
        logger.info("\n{}", productsByType);
    }
    
    @Test
    void getProductsAndItsQuantity() {
        final List<ProductProjectionClass> productByTypeAndGetQuantity = productRepository.findByProductTypeAndGetQuantity("electronics");
        logger.info("\n{}", productByTypeAndGetQuantity);
    }

    @Test
    void checkPersistLayer() {
        productService.checkProduct();
    }

}
