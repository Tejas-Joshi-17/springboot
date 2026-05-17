package com.sarvatra.service;

import com.sarvatra.entities.Insurance;
import com.sarvatra.entities.Patient;
import com.sarvatra.repository.InsuranceRepository;
import com.sarvatra.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);    // Dirty patient
        insurance.setPatient(patient);      // Optional

        return insurance;
    }

}
