package com.sarvatra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = application-name of calling service
// path = api starting path
@FeignClient(name = "inventory-service", path = "/api/v1")
public interface InventoryOpenFeignClient {

    @GetMapping("/products/get-price/{id}")
    Double getItemPrice(@PathVariable Long id);

    @GetMapping(path = "/cbs/credit/{id}")
    ResponseEntity<String> creditToBeneficiaryAccount(@PathVariable Long id);

}
