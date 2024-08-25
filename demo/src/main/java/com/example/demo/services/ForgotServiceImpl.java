package com.example.demo.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.model.ForgotPassword;
import com.example.demo.repositories.ForgotRepository;
@Service
public class ForgotServiceImpl implements ForgotService{

    private final ForgotRepository forgotRepository;

    public ForgotServiceImpl(ForgotRepository forgotRepository) {
        this.forgotRepository = forgotRepository;
    }

    @Override
    public Integer generateOtp() {
        // TODO Auto-generated method stub
        Random random = new Random();
        return random.nextInt(1_000, 9_999);
    }

    @Override
    public ForgotPassword save(ForgotPassword forgotPassword) {
        // TODO Auto-generated method stub
        return forgotRepository.save(forgotPassword);
    }

    @Override
    public Optional<ForgotPassword> findByOtpAndUserEmail(Integer otp, String email) {
        // TODO Auto-generated method stub
        return forgotRepository.findByOtpAndUserEmail(otp, email);
    }

    @Override
    public void delete(ForgotPassword forgotPassword) {
        // TODO Auto-generated method stub
        forgotRepository.delete(forgotPassword);
    }
}
