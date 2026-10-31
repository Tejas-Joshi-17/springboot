package com.sarvatra.dto;

public record PaymentResponse(
        String transactionId,
        String status
) {
}

