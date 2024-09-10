package com.example.demo.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
