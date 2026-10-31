package com.sarvatra.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvatra.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ObjectMapper objectMapper;

    public List<ProductDto> getAllProducts() throws IOException {
        log.info("Fetching all inventory items");

        ClassPathResource resource = new ClassPathResource("get-all-products-response.json");

        InputStream inputStream = resource.getInputStream();

        return objectMapper.readValue(
                inputStream,
                new TypeReference<>() {}
        );
    }

    public ProductDto getProductById(Long id) throws IOException {
        log.info("Fetching Product with id :- {}", id);
        ClassPathResource resource = new ClassPathResource("get-product-by-id-response.json");

        InputStream inputStream = resource.getInputStream();

        ProductDto productDto =  objectMapper.readValue(
                inputStream,
                new TypeReference<>() {}
        );
        productDto.setId(id);

        return productDto;
    }

}
