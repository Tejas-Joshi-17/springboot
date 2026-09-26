package com.sarvatra.service;

import com.sarvatra.entities.Patient;
import com.sarvatra.repository.InsuranceRepository;
import com.sarvatra.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient deletePatient(Long patientId) {
        final Patient patient = patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId);
        return patient;
    }

    @Transactional
    public Patient deleteInsuranceofPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);
        return patient;
    }

    public Patient updatePatientMailId(Long patientId, String email) {
        final Optional<Patient> patient = patientRepository.findById(patientId);
        Patient patient1 = patient.get();
        patient1.setEmail(email);
        return patient1;
    }

    @Transactional
    public Patient updatePatientEmailId(Long patientId, String email) {
        final Optional<Patient> patient = patientRepository.findById(patientId);
        Patient patient1 = patient.get();
        patient1.setEmail(email);
        return patient1;
    }

}
