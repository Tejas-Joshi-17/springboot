package com.sarvatra.service;

import com.sarvatra.entities.Appointment;
import com.sarvatra.entities.Doctor;
import com.sarvatra.entities.Patient;
import com.sarvatra.repository.AppointmentRepository;
import com.sarvatra.repository.DoctorRepository;
import com.sarvatra.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        final Patient patient = patientRepository.findById(patientId).orElseThrow();
        final Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;
    }

}
