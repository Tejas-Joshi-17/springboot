package com.sarvatra.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    @NotNull
    private Long id;

}
