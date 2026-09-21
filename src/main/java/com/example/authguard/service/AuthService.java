package com.example.authguard.service;

import com.example.authguard.dto.RegisterRequest;
import com.example.authguard.entity.OtpVerification;
import com.example.authguard.entity.User;
import com.example.authguard.repository.OtpRepository;
import com.example.authguard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;
    private final OtpRepository otpRepository;
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       OtpService otpService,
                       EmailService emailService,
                       OtpRepository otpRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.emailService = emailService;
        this.otpRepository = otpRepository;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        String otp = otpService.generateOtp(request.getEmail());

        emailService.sendOtpEmail(request.getEmail(), otp);
        return "Registration successful. OTP generated.";
    }



    public String verifyOtp(String email, String otp) {

        OtpVerification otpVerification =
                otpRepository.findByEmail(email)
                        .orElse(null);

        if (otpVerification == null) {
            return "OTP not found";
        }

        if (LocalDateTime.now().isAfter(otpVerification.getExpiryTime())) {
            otpRepository.deleteByEmail(email);
            return "OTP has expired";
        }

        if (!otpVerification.getOtp().equals(otp)) {
            return "Invalid OTP";
        }

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        user.setVerified(true);
        userRepository.save(user);

        otpRepository.deleteByEmail(email);

        return "Email verified successfully";
    }


    public  String resendOtp(String email)
    {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user== null)
        {
            return"user not found";

        }
        if(user.isVerified())
        {
            return "Email is  already veriffiied ";

        }
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
        return  "New otp sent sucessfully";


    }


}