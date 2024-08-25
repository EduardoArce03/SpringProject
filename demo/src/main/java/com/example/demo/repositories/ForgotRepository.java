package com.example.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ForgotPassword;

@Repository
public interface ForgotRepository extends JpaRepository <ForgotPassword, Long> {
    @Query("SELECT f FROM ForgotPassword f WHERE f.otp = ?1 AND f.user.email = ?2")
    Optional<ForgotPassword> findByOtpAndUserEmail(Integer otp, String email);
}
