package com.sarvatra;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringTestingApplicationTests {

    @BeforeEach
    void setUp() {
        log.info("Starting the method, setting the config");
    }

    @AfterEach
    void tearDown() {
        log.info("Tearing down the method");
    }

    @BeforeAll
    static void setUpOnce() {
        log.info("Setup Once ...");
    }

    @AfterAll
    static void tearDownOnce() {
        log.info("Tearing down all ...");
    }

    @Test
    void testNumberOne() {
        log.info("Test 1 is Runed");
    }

    @Test
    void testNumberTwo() {
        log.info("Test 2 is Runed");
    }

}