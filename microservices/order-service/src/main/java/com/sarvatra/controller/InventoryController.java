package com.sarvatra.controller;

import com.sarvatra.clients.InventoryOpenFeignClient;
import com.sarvatra.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
@Slf4j
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllInventories() {
        log.info("Fetching all Inventories");
        return inventoryOpenFeignClient.getAllInventory();
    }

}
