package com.sarvatra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service", path = "/orders")
public interface OrdersFeignClient {

    @GetMapping("/api/v1/orders")
    ResponseEntity<String> helloOrders();

}
