package com.example.authguard.service;

import com.example.authguard.entity.OtpVerification;
import com.example.authguard.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOtp(String email) {

        String otp = String.format("%06d", new Random().nextInt(1_000_000));

        OtpVerification otpVerification = new OtpVerification();

        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setExpiryTime(
                LocalDateTime.now().plusMinutes(5)
        );

        otpRepository.deleteByEmail(email);
        otpRepository.save(otpVerification);

        return otp;
    }
}