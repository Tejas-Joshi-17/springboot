package com.sarvatra.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputUserRole, ConstraintValidatorContext context) {
        if(inputUserRole == null) return false;
        List<String> roles = List.of("USER", "ADMIN");
        return roles.contains(inputUserRole);
    }
}
