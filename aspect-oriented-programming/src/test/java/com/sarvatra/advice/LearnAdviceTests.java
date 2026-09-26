package com.sarvatra.advice;

import com.sarvatra.services.learn.advice.LearnAdviceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class LearnAdviceTests {

    @Autowired
    private LearnAdviceService learnAdviceService;

    @Test
    void beforeAdvice() {
        learnAdviceService.beforeAdvice();
    }

    @Test
    void afterAdvice() {
        learnAdviceService.afterAdvice();
    }

    @Test
    void afterReturning() {
        learnAdviceService.afterReturning();
    }

    @Test
    void afterThrowing() {
        learnAdviceService.afterThrowing();
    }

}