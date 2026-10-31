package com.sarvatra.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/dashboard")
public class TomcatNonBlockingController {

    private final TaskScheduler taskScheduler;

    @RequestMapping(path = "/sign-up")
    public ResponseEntity<String> signUp() {
        final long startTime = System.currentTimeMillis();

        log.info("Sign-up Operation is started -{}", Thread.currentThread().getName());

        taskScheduler.schedule(() -> {
            log.info("Student Profile Creation Started-{}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Student Profile Creation Completed ... - {}", Thread.currentThread().getName());
        }, Instant.now());

        log.info("Sign-up Operation is Ended -{}", Thread.currentThread().getName());

        final long endTime = System.currentTimeMillis();
        final long totalTimeTaken = endTime - startTime;
        log.info("Total Time Taken :- {} milliseconds", totalTimeTaken);

        return new ResponseEntity<>("Sign Up Successfully", HttpStatusCode.valueOf(200));
    }

}
