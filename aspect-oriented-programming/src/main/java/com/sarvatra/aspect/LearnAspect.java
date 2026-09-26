package com.sarvatra.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LearnAspect {

    @Before("execution(* com.sarvatra.services.learn.advice.LearnAdviceService.beforeAdvice(..))")
    public void learnBeforeAdvice() {
        log.info("@Before Advice method called");
    }

    @After("execution(* com.sarvatra.services.learn.advice.LearnAdviceService.afterAdvice(..))")
    public void learnAfterAdvice() {
        log.info("@After Advice method called");
    }

    @AfterReturning("execution(* com.sarvatra.services.learn.advice.LearnAdviceService.afterReturning(..))")
    public void learnAfterReturningAdvice() {
        log.info("@AfterReturning Advice method called");
    }

    @AfterThrowing("execution(* com.sarvatra.services.learn.advice.LearnAdviceService.afterThrowing(..))")
    public void learnAfterThrowingAdvice() {
        log.info("@AfterThrowing Advice method called");
    }

}