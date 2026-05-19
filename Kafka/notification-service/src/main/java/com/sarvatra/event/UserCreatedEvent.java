package com.sarvatra.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserCreatedEvent {

    private Long id;
    private String email;

}

