package com.thanh.repository;

import com.thanh.model.VerificationCode;
import com.thanh.response.SignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByEmail(String email);
    VerificationCode findByOtp(String otp);
}
