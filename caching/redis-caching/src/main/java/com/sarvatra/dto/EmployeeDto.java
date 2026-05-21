package com.sarvatra.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {
    private Long id;
    private String email;
    private String name;
    private String salary;
}
