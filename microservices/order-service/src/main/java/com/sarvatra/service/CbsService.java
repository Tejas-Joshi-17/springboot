package com.sarvatra.service;

import com.sarvatra.clients.InventoryOpenFeignClient;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CbsService {

    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    @Retry(name = "beneficiaryService", fallbackMethod = "initiateReversal")
    public ResponseEntity<String> initiateCheckStatusForCredit(Long id) {
        log.info("Initiate Check Status Request");
        return inventoryOpenFeignClient.creditToBeneficiaryAccount(id);
    }

    @RateLimiter(name = "cbsRateLimiter", fallbackMethod = "cbsServiceOverload")
    public ResponseEntity<String> getTxnHistory() {
        return new ResponseEntity<>("Transaction data served", HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<String> initiateReversal(Long id, Throwable throwable) {
        log.info("Getting error for transaction - {} :- {}", id, throwable.getMessage());
        log.info("Transaction has been Reversed");
        return new ResponseEntity<>("Transaction has been Reversed", HttpStatusCode.valueOf(503));
    }

    public ResponseEntity<String> cbsServiceOverload(Throwable throwable) {
        log.info("Getting error for transaction - {}", throwable.getMessage());
        log.info("CBS Overloaded");
        return new ResponseEntity<>("CBS Overloaded", HttpStatusCode.valueOf(503));
    }

}
