package com.sarvatra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNo;

    @OneToOne    // one passport -> one person
    @JoinColumn(name = "person_id") // Owning Side
    private Person person;

}
