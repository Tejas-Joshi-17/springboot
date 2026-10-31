package com.sarvatra.controller;

import com.sarvatra.dto.PaymentRequest;
import com.sarvatra.dto.PaymentResponse;
import com.sarvatra.dto.TransferRequest;
import com.sarvatra.dto.TransferResponse;
import com.sarvatra.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cbs")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(path = "/payments")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

    @PostMapping(path = "/transfer-amount")
    public ResponseEntity<TransferResponse> transferAmount(@RequestBody TransferRequest request) {
        return paymentService.processTransfer(request);
    }

}




