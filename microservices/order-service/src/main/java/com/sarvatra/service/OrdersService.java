package com.sarvatra.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvatra.clients.InventoryOpenFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {

    private final InventoryOpenFeignClient inventoryOpenFeignClient;
    private final ObjectMapper objectMapper;

    public List<OrderRequestDto> getAllOrders() throws IOException {
        log.info("Fetching all orders");

        ClassPathResource resource = new ClassPathResource("all-orders-response.json");

        InputStream inputStream = resource.getInputStream();

        return objectMapper.readValue(
                inputStream,
                new TypeReference<>() {}
        );
    }

    public OrderRequestDto getOrderById(Long id) throws IOException {

        log.info("Fetching order with id :- {}", id);

        ClassPathResource resource = new ClassPathResource("order-by-id-response.json");

        InputStream inputStream = resource.getInputStream();

        OrderRequestDto orderRequestDto =  objectMapper.readValue(inputStream, new TypeReference<>() {} );
        orderRequestDto.setId(id);

        return orderRequestDto;
    }

}
