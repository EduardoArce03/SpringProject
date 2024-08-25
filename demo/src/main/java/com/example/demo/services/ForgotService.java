package com.example.demo.services;

import java.util.Optional;

import com.example.demo.model.ForgotPassword;

public interface ForgotService {

    Integer generateOtp();

    ForgotPassword save(ForgotPassword forgotPassword);

    Optional<ForgotPassword> findByOtpAndUserEmail(Integer otp, String email);

    void delete(ForgotPassword forgotPassword);
}
