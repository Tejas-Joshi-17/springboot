package com.sarvatra.clients;

import com.sarvatra.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// name = application-name of calling service
// path = api starting path
@FeignClient(name = "order-service", path = "/api/v1")
public interface OrdersFeignClient {

    @GetMapping("/orders")
    ResponseEntity<List<OrderRequestDto>> getAllOrders();

}
