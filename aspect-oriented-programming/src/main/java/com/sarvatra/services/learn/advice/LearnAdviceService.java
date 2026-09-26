package com.sarvatra.services.learn.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LearnAdviceService {

    public void beforeAdvice() {
        log.info("Before Advice Method Called");
    }

    public void afterAdvice() {
        log.info("After Advice Method Called");
    }

    public String afterReturning() {
        log.info("AfterReturning Advice Method Called");
        return "Hello from Tejas";
    }

    public void afterThrowing() {
        log.info("AfterThrowing Advice Method Called");
        throw new RuntimeException("Throwing Tejas Exception");
    }

}