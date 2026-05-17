package com.sarvatra.mapping;

import com.sarvatra.entities.Appointment;
import com.sarvatra.entities.Insurance;
import com.sarvatra.entities.Patient;
import com.sarvatra.service.AppointmentService;
import com.sarvatra.service.InsuranceService;
import com.sarvatra.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OneToOneMappingTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    @Order(1)
    void assignInsuranceToPatientWithIdOne() {
        Insurance insurance = com.sarvatra.entities.Insurance.builder()
                .provider("HDFC Ergo")
                .policyNumber("HDFC_23F")
                .validUtil(LocalDate.of(2030, 1, 1))
                .build();

        final Insurance insurance1 = insuranceService.assignInsuranceToPatient(insurance, 1L);
        log.info("insurance assigned to patient of id-1 :- {}", insurance1);
    }

    @Test
    @Order(2)
    void createNewAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 4, 5, 2, 3, 10))
                .reason("Cancer")
                .build();

        final Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
        log.info("created appointment :- {}", newAppointment);
    }

    @Test
    @Order(3)
    void assignInsuranceToPatientWithIdThree() {
        Insurance insurance = com.sarvatra.entities.Insurance.builder()
                .provider("ICICI Prud Fund")
                .policyNumber("ICICI-17")
                .validUtil(LocalDate.of(2032, 2, 3))
                .build();

        final Insurance insurance1 = insuranceService.assignInsuranceToPatient(insurance, 3L);
        log.info("insurance assigned to patient of id-3 :- {}", insurance1);
    }
    @Test
    @Order(4)
    void deleteInsuranceOfAnPatientOfIdThree() {
        final Patient patient = patientService.deleteInsuranceofPatient(3L);
        log.info("Delete Insurance of Patient with id :- {}", patient.getId());
        log.info("Patient updated info :- {}", patient);
    }

    @Test
    @Order(5)
    void deletePatientByIdOne() {
        final Patient patient = patientService.deletePatient(1L);
        log.info("deleted Patient :- {}", patient);
    }

    @Test
    @Order(6)
    void deletePatientByIdTwo() {
        final Patient patient = patientService.deletePatient(2L);
        log.info("deleted Patient :- {}", patient);
    }

}