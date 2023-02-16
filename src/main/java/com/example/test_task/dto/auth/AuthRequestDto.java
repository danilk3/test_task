package com.example.test_task.dto.auth;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}
