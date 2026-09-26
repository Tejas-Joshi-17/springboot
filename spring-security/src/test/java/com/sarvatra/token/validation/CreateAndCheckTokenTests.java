package com.sarvatra.token.validation;

import com.sarvatra.entities.User;
import com.sarvatra.services.JwtTokenService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 1. Define the ordering strategy
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateAndCheckTokenTests {

    private static final Logger log = LoggerFactory.getLogger(CreateAndCheckTokenTests.class);

    @Autowired
    private JwtTokenService jwtTokenService;

    private String jwtToken;

//    @Test
//    @Disabled
//    @Order(1)
//    void generatedToken() {
//        User user = new User(2L, "Tejas Joshi","joshitejas188@gmail.com", "Tejas@987");
//        jwtToken = jwtTokenService.generateAccessToken(user);
//    }

    @Test
    @Order(2)
    void printJwtToken() {
        log.info("Generated token is :- {}", jwtToken);
    }

    @Test
    @Order(3)
    void checkJwtToken() {
        final Long userId = jwtTokenService.getUserIdFromToken(jwtToken);
        log.info("user with id :- {} present", userId);
    }

    @Test
    @Order(4)
    void checkJwtTokenAgain() throws InterruptedException {
        Thread.sleep(70000);
        final Long userId = jwtTokenService.getUserIdFromToken(jwtToken);
        log.info("user with id :- {} present", userId);
    }
}
