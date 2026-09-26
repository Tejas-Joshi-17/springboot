package com.sarvatra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class PersistenceLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceLayerApplication.class, args);
	}
}
