package com.sarvatra.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/students")
public class TomcatBlockingController {

    @GetMapping(path = "/name")
    public ResponseEntity<String> getStudentName() throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        log.info("Student Data Fetching Started ...");

        Thread.sleep(5000);

        log.info("Student Data Fetching Completed ...");
        final long endTime = System.currentTimeMillis();
        final long totalTimeTaken = endTime - startTime;
        log.info("Total Time Taken is :- {} milliseconds", totalTimeTaken);

        return new ResponseEntity<>("Tejas Joshi", HttpStatusCode.valueOf(200));

    }

}

