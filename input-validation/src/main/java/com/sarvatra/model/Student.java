package com.sarvatra.model;

import com.sarvatra.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    @NotNull(message = "student id must be present")
    private Long id;

    @NotEmpty(message = "student name must be present")
    private String studentName;

    @EmployeeRoleValidation
    private String employeeRole;

}
