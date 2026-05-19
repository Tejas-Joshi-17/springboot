package com.sarvatra.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreatedEvent {

    private Long id;
    private String email;

}
