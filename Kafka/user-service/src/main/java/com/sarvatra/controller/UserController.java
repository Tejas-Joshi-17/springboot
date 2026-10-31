package com.sarvatra.controller;

import com.sarvatra.dto.CreateUserRequestDto;
import com.sarvatra.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final Environment environment;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(path = "/order")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created");
    }

    @PostMapping(path = "/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        final String serviceTopic = environment.getProperty("kafka.topic.order-service", "");
        kafkaTemplate.send(serviceTopic, "10", message);
        return new ResponseEntity<>("Message Queued", HttpStatusCode.valueOf(200));
    }

}
