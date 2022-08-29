package com.example.demojwt.dto.request;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
}
