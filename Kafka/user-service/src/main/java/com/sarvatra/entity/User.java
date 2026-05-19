package com.sarvatra.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity()
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;


}
