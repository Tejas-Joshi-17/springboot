package com.sarvatra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private String accountId;
    private String destinationAccountId;
    private BigDecimal amount;
    private String currency;
    private String idempotencyKey;

}


