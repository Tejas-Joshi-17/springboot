package com.sarvatra.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.sarvatra.services.impl.ShipmentServiceImpl.*(..))")
    public void logShipmentServices(JoinPoint joinPoint) {
        log.info("Before method called from ShipmentService, {}", joinPoint.getKind());
        log.info("Before method called from ShipmentService, {}", joinPoint.getSignature());
    }

    @Before("within(com.sarvatra.services.impl.*)")
    public void logShipmentServices2(JoinPoint joinPoint) {
        log.info("Before everything called from impl package, {}", joinPoint.getKind());
        log.info("Before everything called from impl package, {}", joinPoint.getSignature());
    }

    @Before("@annotation(jakarta.transaction.Transactional)")
    public void logTransactionalMethod(JoinPoint joinPoint) {
        log.info("Calling Transactional Method - {}", joinPoint.getSignature());
    }

}