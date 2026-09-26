package com.sarvatra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne                         // one department -> one docker
    @JoinColumn(nullable = false)
    private Doctor headDocker;        // owning side

    @ManyToMany                                          // one department -> many docker
    private Set<Doctor> doctors = new HashSet<>();       // one docker -> many department

}



