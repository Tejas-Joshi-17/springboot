package com.sarvatra.entity.management;

import com.sarvatra.entities.Patient;
import com.sarvatra.repository.PatientRepository;
import com.sarvatra.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
class EntityManagementTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    void getPatientWithIdWithoutTransactionalContext() {
        final Optional<Patient> patient1 = patientRepository.findById(1L);
        final Optional<Patient> patient2 = patientRepository.findById(1L);
        log.info("Both Patients Equal ? :- {}", patient1.get() == patient2.get());
    }

    @Test
    @Transactional
    void getPatientWithIdWithTransactionalContext() {
        final Optional<Patient> patient1 = patientRepository.findById(1L);
        final Optional<Patient> patient2 = patientRepository.findById(1L);
        log.info("Both Patients are Equal ? :- {}", patient1.get() == patient2.get());
    }

    @Test
    void updatePatientInfoWithoutTransactionalContext() {
        Optional<Patient> patientInfo = patientRepository.findById(1L);
        log.info("Old Patient Info :- {}", patientInfo.get());
        patientInfo.get().setEmail("joshitejas188@gmail.com");
        patientRepository.save(patientInfo.get());
        log.info("Updated Patient Info :- {}", patientInfo.get());
    }

    @Test
    void updatePatientInfoWithTransactionalContext() {
        Optional<Patient> patientInfo = patientRepository.findById(1L);
        log.info("Old Patient Info :- {}", patientInfo.get());
        final Patient patient = patientService.updatePatientEmailId(1L, "joshitejas188@gmail.com");
        log.info("Updated Patient Info :- {}", patient);
    }

}
