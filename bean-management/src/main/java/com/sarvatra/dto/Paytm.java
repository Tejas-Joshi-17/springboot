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
public class Paytm {

    private static final Logger log = LoggerFactory.getLogger(Paytm.class);

    @PostConstruct
    public void init() {
        log.info("PaytmService (Object) initiated");
    }

    public void pay() {
        log.info("Paying with using PaytmService");
    }

    @PreDestroy
    public void closing() {
        log.info("Destroying PaytmService (Object)");
    }

}
