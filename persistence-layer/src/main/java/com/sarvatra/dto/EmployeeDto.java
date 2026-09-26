package com.sarvatra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDateTime dateOfJoining;
    private boolean isActive;

}
