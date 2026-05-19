package com.sarvatra.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class EmployeeDto implements Serializable {
    private Long id;
    private String email;
    private String name;
    private String salary;
}
