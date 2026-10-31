package com.sarvatra.service;

import com.sarvatra.dto.CreateUserRequestDto;
import com.sarvatra.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Environment environment;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public void createUser(CreateUserRequestDto createUserRequestDto) {

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(createUserRequestDto.getId())
                .email(createUserRequestDto.getEmail())
                .build();

        final String deliveryTopic = environment.getProperty("kafka.topic.delivery-service", "");

        kafkaTemplate.send(deliveryTopic, userCreatedEvent.getId(), userCreatedEvent);
    }

}
