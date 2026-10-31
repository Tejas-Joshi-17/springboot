package com.sarvatra.controller;

import com.sarvatra.clients.OrdersFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {

    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public ResponseEntity<String> fetchFromOrdersService() {

        ServiceInstance orderService = discoveryClient.getInstances("order-service").get(0);

        return new ResponseEntity<>(
                restClient.get()
                        .uri(orderService.getUri() + "/api/v1/orders/helloOrders")
                        .retrieve()
                        .body(String.class),
                HttpStatusCode.valueOf(200)
        );
    }

    @GetMapping(path = "/fetch-all-orders")
    public ResponseEntity<List<OrderRequestDto>> fetchAllOrdersFromOrderService() {
        return ordersFeignClient.getAllOrders();
    }

}
