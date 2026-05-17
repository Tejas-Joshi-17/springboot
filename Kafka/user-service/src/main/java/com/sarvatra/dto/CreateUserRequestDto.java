package com.sarvatra.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserRequestDto {

    private Long id;
    private String name;
    private String email;

}
