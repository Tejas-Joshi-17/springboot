package com.sarvatra.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final Environment environment;

    @Bean
    public NewTopic serviceTopic() {
        final String serviceTopic = environment.getProperty("kafka.topic.order-service");
        return new NewTopic(serviceTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic deliveryTopic() {
        final String deliveryTopic = environment.getProperty("kafka.topic.delivery-service");
        return new NewTopic(deliveryTopic, 3, (short) 1);
    }

}
