package com.sarvatra.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"patient", "doctor"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)                 // many-appointment -> one-patient
    @JoinColumn(nullable = false)
    private Patient patient;   // Owning side

    @ManyToOne(fetch = FetchType.LAZY)                  // many-appointment -> one-docker
    @JoinColumn(nullable = false)
    private Doctor doctor;     // Owning side

}
