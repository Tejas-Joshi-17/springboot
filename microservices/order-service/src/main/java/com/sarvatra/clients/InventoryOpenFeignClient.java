package com.sarvatra.clients;

import com.sarvatra.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// name = application-name of calling service
// path = api starting path
@FeignClient(name = "inventory-service", path = "/api/v1")
public interface InventoryOpenFeignClient {

    @GetMapping("/inventories")
    ResponseEntity<List<ProductDto>> getAllInventory();

}
