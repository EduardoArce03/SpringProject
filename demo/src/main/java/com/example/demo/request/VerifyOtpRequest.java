package com.example.demo.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private Integer otp;
    private String email;
}
