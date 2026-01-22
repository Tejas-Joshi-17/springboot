package com.sarvatra.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    @NotNull(message = "student-id must be not null")
    private Long id;

    @NotBlank(message = "Student name must be not blank")
    private String name;

    @Email(message = "email must contains '@'")
    private String email;

    @PositiveOrZero(message = "marks must be greater than or equal to 0")
    @Max(value = 100, message = "marks must be less than or equal to 100")
    private int marks;
}
