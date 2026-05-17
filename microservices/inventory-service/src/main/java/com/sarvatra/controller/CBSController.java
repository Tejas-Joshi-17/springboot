package com.sarvatra.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/cbs")
public class CBSController {

    @GetMapping(path = "/credit/{id}")
    public ResponseEntity<String> creditToBeneficiaryAccount(@PathVariable Long id) {
        return new ResponseEntity<>("Amount Credited", HttpStatusCode.valueOf(200));
    }
}
