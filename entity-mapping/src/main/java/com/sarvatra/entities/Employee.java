package com.sarvatra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne     // many employees -> one department
    @JoinColumn(name = "department_id") // Owning Side
    private Department department;

}
