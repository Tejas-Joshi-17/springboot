package com.sarvatra.controller;

import com.sarvatra.clients.InventoryOpenFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import com.sarvatra.dto.ProductDto;
import com.sarvatra.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() throws IOException {
        log.info("Fetching all orders via controller");
        final List<OrderRequestDto> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) throws IOException {
        log.info("Fetching all order with id :- {} via controller", id);
        final OrderRequestDto order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<List<ProductDto>> getItemPrice(@PathVariable Long id) {
        log.info("Fetching order with id :- {} via controller", id);
        return inventoryOpenFeignClient.getAllInventory();
    }

}
