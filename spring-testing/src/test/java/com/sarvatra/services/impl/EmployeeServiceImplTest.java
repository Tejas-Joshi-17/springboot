package com.sarvatra.services.impl;

import com.sarvatra.dto.EmployeeDto;
import com.sarvatra.entities.Employee;
import com.sarvatra.exceptions.ResourceNotFoundException;
import com.sarvatra.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp() {
        mockEmployee = Employee.builder()
                .id(1L)
                .email("anuj@gmail.com")
                .name("Anuj")
                .salary(200L)
                .build();

        mockEmployeeDto = EmployeeDto.builder()
                .id(mockEmployee.getId())
                .name(mockEmployee.getName())
                .email(mockEmployee.getEmail())
                .salary(mockEmployee.getSalary())
                .build();
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto() {

        Long id = mockEmployee.getId();

        // Stubbing
        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(mockEmployee));

        // Calling Method
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());

        // Verifying whether mocked object's method (findById()) called ?
        verify(employeeRepository, only()).findById(id);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException() {

        // Stubbing
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        // Verifying whether mocked object's method (findById()) called ?
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_ThenCreateNewEmployee() {

        // Stubbing
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        // Calling Method
        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException() {

        // Stubbing
        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));

        assertThatThrownBy(() -> employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: " + mockEmployee.getEmail());

        // Verifying whether mocked object's method (findByEmail()) called ? & save() method NOT called
        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExists_thenThrowException() {

        // Stubbing
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        // Verifying whether mocked object's method (findById()) called ? & save() method NOT called
        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException() {

        // Stubbing
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));

        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setEmail("random@gmail.com");

        assertThatThrownBy(() -> employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");

        // Verifying whether mocked object's method (findById()) called ? & save() method NOT called
        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee() {

        // Stubbing
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random name");
        mockEmployeeDto.setSalary(199L);

        Employee newEmployee = Employee.builder()
                        .id(mockEmployee.getId())
                        .name(mockEmployee.getName())
                        .email(mockEmployee.getEmail())
                        .salary(mockEmployee.getSalary())
                        .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto);

        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        // Verifying whether mocked object's method (findById()) called ? & save() method called
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());
    }

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExists_thenThrowException() {

        // Stubbing
        when(employeeRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + 1L);

        // Verifying whether mocked object's method (deleteById()) NEVER called
        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    void testDeleteEmployee_whenEmployeeIsValid_thenDeleteEmployee() {

        // Stubbing
        when(employeeRepository.existsById(1L)).thenReturn(true);

        assertThatCode(() -> employeeService.deleteEmployee(1L))
                .doesNotThrowAnyException();

        // Verifying whether mocked object's method (deleteById()) called
        verify(employeeRepository).deleteById(1L);
    }

}