package com.sarvatra.dto;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @PostConstruct
    public void startApplication() {
        log.info("Creating beans (Objects)");
    }

    public void running() {
        log.info("Application is Running");
    }

    @PreDestroy
    public void exitingApplication() {
        log.info("Destroying beans (Objects)");
    }
}
