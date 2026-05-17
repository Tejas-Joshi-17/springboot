package com.sarvatra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.order-service}")
    private String serviceTopic;

    @Value("${kafka.topic.delivery-service}")
    private String deliveryTopic;

    @Bean
    public NewTopic serviceTopic() {
        return new NewTopic(serviceTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic deliveryTopic() {
        return new NewTopic(deliveryTopic, 3, (short) 1);
    }

}
