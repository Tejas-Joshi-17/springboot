package com.sarvatra.controller;

import com.sarvatra.clients.OrdersFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import com.sarvatra.dto.ProductDto;
import com.sarvatra.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
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
    public ResponseEntity<String> fetchAllOrdersFromOrderService() {
        return ordersFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id) {
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping(path = "/get-price/{id}")
    public Double getPrice(@PathVariable Long id) {
        return productService.getItemPrice(id);
    }

}
