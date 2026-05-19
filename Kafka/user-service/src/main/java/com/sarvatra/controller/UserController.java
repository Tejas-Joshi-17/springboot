package com.sarvatra.controller;

import com.sarvatra.dto.CreateUserRequestDto;
import com.sarvatra.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Value("${kafka.topic.order-service}")
    private String serviceTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;

    @PostMapping(path = "/order")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created");
    }

    @PostMapping(path = "/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(serviceTopic, String.valueOf(i % 2), message);
        }

        return new ResponseEntity<>("Message Queued", HttpStatusCode.valueOf(200));
    }

}
