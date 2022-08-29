package com.example.demojwt.dto.request;

import lombok.Data;

import java.util.Set;
@Data
public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
