package com.sarvatra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class ProfileManagementApplication implements CommandLineRunner {

    private final Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ProfileManagementApplication.class, args);
	}

    @Override
    public void run(String... args) {
        final String envName = environment.getProperty("environment.type");
        log.info("Current Environment Name :- {}", envName);
    }
}
