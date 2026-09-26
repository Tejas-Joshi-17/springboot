package com.sarvatra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EntityMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityMappingApplication.class, args);
    }

}
