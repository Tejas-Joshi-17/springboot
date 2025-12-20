package com.sarvatra.dto;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Person {

    private static final Logger log = LoggerFactory.getLogger(Person.class);

    @PostConstruct
    public void walkUp() {
        log.info("-------------------------->");
        log.info("Person (Object) is Creating");
    }

    public void living() {
        log.info("Person is Living Happily");
    }

    @PreDestroy
    public void sleeping() {
        log.info("Person (Object) is Dead Now");
    }

}
