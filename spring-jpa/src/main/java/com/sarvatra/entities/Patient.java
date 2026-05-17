package com.sarvatra.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"insurance", "appointmentList"})
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroup bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)       // One-Patient -> One-Insurance
    @JoinColumn(name = "patient_insurance")
    private Insurance insurance;    // Owning Side

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL})
    private List<Appointment> appointmentList = new ArrayList<>();  // Inverse Side

    public enum BloodGroup {
        A_POSITIVE,
        A_NEGATIVE,
        B_POSITIVE,
        B_NEGATIVE,
        AB_POSITIVE,
        AB_NEGATIVE,
        O_POSITIVE,
        O_NEGATIVE
    }

}
