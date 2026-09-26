package com.sarvatra.services;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.entity.EmployeeEntity;
import com.sarvatra.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeMngService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto getEmployeeById(Long id) {
        final Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            EmployeeEntity employeeData = employee.get();
            return EmployeeDto.builder()
                    .id(employeeData.getId())
                    .name(employeeData.getName())
                    .age(employeeData.getAge())
                    .email(employeeData.getEmail())
                    .dateOfJoining(employeeData.getDateOfJoining())
                    .isActive(employeeData.getIsActive())
                    .build();
        }
        return new EmployeeDto();

    }

}
