package com.sarvatra.service;

import com.sarvatra.dto.CreateUserRequestDto;
import com.sarvatra.entity.User;
import com.sarvatra.event.UserCreatedEvent;
import com.sarvatra.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${kafka.topic.delivery-service}")
    private String deliveryTopic;

    private final UserRepository userRepository;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public void createUser(CreateUserRequestDto createUserRequestDto) {
        User user = User.builder()
                .id(createUserRequestDto.getId())
                .name(createUserRequestDto.getName())
                .email(createUserRequestDto.getEmail())
                .build();

        User createdUser = userRepository.save(user);

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .build();

        kafkaTemplate.send(deliveryTopic, userCreatedEvent.getId(), userCreatedEvent);
    }

}
