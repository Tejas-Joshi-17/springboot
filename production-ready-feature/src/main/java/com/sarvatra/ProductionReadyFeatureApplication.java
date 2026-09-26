package com.sarvatra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ProductionReadyFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionReadyFeatureApplication.class, args);
    }

}
