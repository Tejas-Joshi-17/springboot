package com.sarvatra.repository;

import com.sarvatra.entities.Product;
import com.sarvatra.interfac.ProductInterface;
import com.sarvatra.interfac.ProductProjectionClass;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    List<Product> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Product> findByQuantityAndPrice(int quantity, BigDecimal price);

    @Query("SELECT p from Product p where p.productName=?1 and p.productType=?2")
    Optional<Product> findByProductNameAndProductType(String productName, String productType);

    List<Product> findAllByOrderByQuantityDesc();

    List<Product> findAll(Sort sort);

    List<Product> findByProductType(String productType, Sort sort);

    List<ProductInterface> findByProductType(String productType);

    @Query("SELECT new com.sarvatra.interfac.ProductProjectionClass(p.productType, p.quantity) FROM Product p where p.productType=?1")
    List<ProductProjectionClass> findByProductTypeAndGetQuantity(String productType);

    List<Product> findByPrice(BigDecimal price);
}
