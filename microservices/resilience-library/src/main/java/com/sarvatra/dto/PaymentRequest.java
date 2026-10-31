package com.sarvatra.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        String accountNumber,
        BigDecimal amount
) {
}


