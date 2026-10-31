package com.sarvatra.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(path = "/students")
public class TomcatThreadManagementController {

    // Here Tomcat Thread is Blocked
    @GetMapping(path = "/getName")
    public ResponseEntity<String> getStudentName() throws InterruptedException {
        log.info("Tomcat Thread is Blocked");
        Thread.sleep(5000);

        return new ResponseEntity<>("Tejas Joshi", HttpStatusCode.valueOf(200));
    }

    @GetMapping(path = "/getAddress")
    public CompletableFuture<ResponseEntity<String>> getStudentAddress() throws InterruptedException {
        log.info("Tomcat Thread is Called");
        Thread.sleep(5000);

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return ResponseEntity.ok("Tejas Joshi");
        });
    }
}
