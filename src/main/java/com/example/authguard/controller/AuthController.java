package com.example.authguard.controller;

import com.example.authguard.dto.LoginRequest;
import com.example.authguard.dto.RegisterRequest;
import com.example.authguard.dto.VerifyOtpRequest;
import com.example.authguard.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        String response = authService.register(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

        String response = authService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/resend-otp")
    public  ResponseEntity<String>resendOtp(@RequestParam String  email)
    {
        String response = authService.resendOtp(email);
        return  ResponseEntity.ok(response);
    }


@PostMapping("/login")
    public  ResponseEntity<String>login(@Valid @RequestBody LoginRequest request)
        {
            String response = authService.login(request);
            return ResponseEntity.ok(response);
        }

}