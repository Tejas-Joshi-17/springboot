package com.sarvatra.service;

import com.sarvatra.dto.PaymentRequest;
import com.sarvatra.dto.PaymentResponse;
import com.sarvatra.exceptions.PaymentGatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentGatewayClient {

    private int attempt = 0;

    public ResponseEntity<PaymentResponse> makePayment(PaymentRequest request) {

        attempt++;

        log.info("Calling payment gateway. Attempt = {}", attempt);

        if (attempt <= 10) {
            throw new PaymentGatewayException("Payment Gateway temporarily unavailable");
        }

        log.info("Payment Done ✔️✔️✔️");
        final PaymentResponse successfulPaymentResponse = new PaymentResponse(
                "TXN-" + request.accountNumber() + System.currentTimeMillis(),
                "SUCCESS"
        );

        return new ResponseEntity<>(successfulPaymentResponse, HttpStatusCode.valueOf(200));
    }
}
