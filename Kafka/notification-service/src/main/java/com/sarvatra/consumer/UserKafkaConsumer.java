package com.sarvatra.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-created-topic")
    public void handleUserCreated(UserCreatedEvent userCreatedEvent) {
        log.info("handleUserCreated: {}", userCreatedEvent);
    }

    @KafkaListener(topics = {"order-topic"}, groupId = "notification-service")
    public void handleOrders1(String message) {
      log.info("Message received by handleOrders1 :- {}", message);
    }

    @KafkaListener(topics = {"order-topic"}, groupId = "notification-service")
    public void handleOrders2(String message) {
        log.info("Message received by handleOrders2 :- {}", message);
    }

    @KafkaListener(topics = {"order-topic"}, groupId = "notification-service")
    public void handleOrders3(String message) {
        log.info("Message received by handleOrders3 :- {}", message);
    }

}
