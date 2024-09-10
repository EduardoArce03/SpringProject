package com.example.demo.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MailBody;
import com.example.demo.model.ForgotPassword;
import com.example.demo.model.Users;
import com.example.demo.repositories.UserRepository;
import com.example.demo.request.ChangePasswordRequest;
import com.example.demo.request.VerifyOtpRequest;
import com.example.demo.services.EmailService;
import com.example.demo.services.ForgotServiceImpl;

@RestController
@RequestMapping("/forgot")

public class ForgotPasswordController {

    private final ForgotServiceImpl forgotService;

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(ForgotServiceImpl forgotService, UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.forgotService = forgotService;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/verifyEmail/{email}")    
    public ResponseEntity<Users> verifyEmail(@PathVariable String email){
        Users user = userRepository.findByEmail(email);
        int otp = forgotService.generateOtp();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("OTP for password reset")
                .body("Your OTP is: " + otp)
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expiryDate(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();
        emailService.sendEmail(mailBody);
        forgotService.save(forgotPassword);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<ForgotPassword> verifyOtp (@RequestBody VerifyOtpRequest verifyOtpRequest){
        Users user = userRepository.findByEmail(verifyOtpRequest.getEmail());
        ForgotPassword forgotPassword = forgotService.findByOtpAndUserEmail(verifyOtpRequest.getOtp(), user.getEmail()).orElseThrow(() -> new RuntimeException("Invalid OTP"));
        if (forgotPassword.getExpiryDate().before(Date.from(Instant.now()))) {
            forgotService.delete(forgotPassword);
            return new ResponseEntity<ForgotPassword>(forgotPassword, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(forgotPassword, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword (@RequestBody ChangePasswordRequest changePassword){
        if (!Objects.equals(changePassword.getPassword(), changePassword.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        String password = passwordEncoder.encode(changePassword.getPassword());
        userRepository.updatePassword(password, changePassword.getEmail());
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);

    }

}
