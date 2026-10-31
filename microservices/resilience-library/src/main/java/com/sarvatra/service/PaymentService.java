package com.sarvatra.service;

import com.sarvatra.dto.PaymentRequest;
import com.sarvatra.dto.PaymentResponse;
import com.sarvatra.dto.TransferRequest;
import com.sarvatra.dto.TransferResponse;
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
public class PaymentService {

    private final PaymentGatewayClient paymentGatewayClient;

    // -------------------------------- Retry Example --------------------------------
    @Retry(name = "paymentService", fallbackMethod = "paymentFallback")
    public ResponseEntity<PaymentResponse> processPayment(PaymentRequest request) {
        log.info("Processing payment...");
        return paymentGatewayClient.makePayment(request);
    }

    public ResponseEntity<PaymentResponse> paymentFallback(PaymentRequest request, Throwable throwable) {
        log.error("Getting error for transaction - {} :- {}", request.accountNumber(), throwable.getMessage());
        log.error("Unable to Process Payment");
        PaymentResponse failurePaymentResponse =  new PaymentResponse("312312", "Fail");
        return new ResponseEntity<>(failurePaymentResponse, HttpStatusCode.valueOf(500));
    }

    // -------------------------------- RateLimiter Example --------------------------------
    @RateLimiter(name = "fundTransferLimit", fallbackMethod = "transferFallback")
    public ResponseEntity<TransferResponse> processTransfer(TransferRequest request) {
        log.info("Processing transfer for account: {}", request.getAccountId());
        TransferResponse transferResponse = new TransferResponse("SUCCESS", "Transfer initiated.");
        return new ResponseEntity<>(transferResponse, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<TransferResponse> transferFallback(TransferRequest request, Throwable throwable) {
        log.warn("Rate limit breached for account: {}", request.getAccountId());

        TransferResponse transferResponse =  new TransferResponse(
                "TOO_MANY_REQUESTS",
                "Too many transfer attempts. Please try again in a minute."
        );

        return new ResponseEntity<>(transferResponse, HttpStatusCode.valueOf(500));
    }

}
