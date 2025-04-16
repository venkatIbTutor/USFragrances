package com.us.USFragrances.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Random random = new SecureRandom();

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + random.nextInt(900000)); // Generate 6-digit OTP
        otpStorage.put(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email, String inputOtp) {
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(inputOtp);
    }

    public void removeOtp(String email) {
        otpStorage.remove(email);
    }
}
