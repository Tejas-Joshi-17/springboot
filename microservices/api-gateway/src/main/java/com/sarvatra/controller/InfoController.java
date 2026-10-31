package com.sarvatra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info")
@RefreshScope
public class InfoController {

    @Value("${developer.name}")
    private String developerName;

    @GetMapping
    public ResponseEntity<String> getDeveloperInfo() {
        return new ResponseEntity<>(
                "Developer is :- " + developerName, HttpStatusCode.valueOf(200));
    }
}
