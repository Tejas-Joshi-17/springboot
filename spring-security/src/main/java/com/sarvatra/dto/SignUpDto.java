package com.sarvatra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sarvatra.entities.enums.Permission;
import com.sarvatra.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpDto {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "roles")
    private Set<Role> roles;

    @JsonProperty(value = "permissions")
    private Set<Permission> permissions;
}
