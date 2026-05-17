package com.sarvatra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity()
@Table(name = "user_table")
public class User {

    private Long id;
    private String name;
    private String email;


}
