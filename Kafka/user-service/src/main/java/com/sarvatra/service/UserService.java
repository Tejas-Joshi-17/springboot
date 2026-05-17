package com.sarvatra.service;

import com.sarvatra.dto.CreateUserRequestDto;
import com.sarvatra.entity.User;
import com.sarvatra.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${kafka.topic.delivery-service}")
    private String deliverTopic;

    private final UserRepository userRepository;

    public void createUser(CreateUserRequestDto createUserRequestDto) {
        User user = User.builder()
                .id(createUserRequestDto.getId())
                .name(createUserRequestDto.getName())
                .email(createUserRequestDto.getEmail())
                .build();

        userRepository.save(user);
    }

}
