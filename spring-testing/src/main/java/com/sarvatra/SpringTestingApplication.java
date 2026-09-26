package com.sarvatra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
public class SpringTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestingApplication.class, args);
	}

}
