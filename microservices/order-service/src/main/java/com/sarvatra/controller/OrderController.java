package com.sarvatra.controller;

import com.sarvatra.clients.InventoryOpenFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import com.sarvatra.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    @GetMapping("/helloOrders")
    public ResponseEntity<String> helloOrders() {
        return new ResponseEntity<>("Hello from Orders Service", HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        log.info("Fetching all orders via controller");
        final List<OrderRequestDto> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching all order with id :- {} via controller", id);
        final OrderRequestDto order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<Double> getItemPrice(@PathVariable Long id) {
        log.info("Fetching order with id :- {} via controller", id);
        final Double price = inventoryOpenFeignClient.getItemPrice(id);
        return ResponseEntity.ok(price);
    }

}
