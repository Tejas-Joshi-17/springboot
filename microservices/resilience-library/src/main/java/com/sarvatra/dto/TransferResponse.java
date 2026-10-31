package com.sarvatra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse {

    private String status;
    private String message;
    private String transactionId;
    private LocalDateTime timestamp;

    // Custom constructor to match the fallback method example
    public TransferResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.transactionId = status.equals("SUCCESS") ? UUID.randomUUID().toString() : null;
        this.timestamp = LocalDateTime.now(ZoneId.of(String.valueOf(ZoneOffset.of("Z"))));
    }

}