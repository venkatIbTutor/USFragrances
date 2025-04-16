package com.us.USFragrances.services;

import com.us.USFragrances.models.User;
import com.us.USFragrances.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(new User()); // Return a new empty User object
    }

    public void registerUser(String email, String password,String phone) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password
        user.setPhone(phone);
        userRepository.save(user);
    }

    public boolean validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword());
    }

    public void sendOtpToUser(String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
    }
    public long getUserCount() {
        return userRepository.count();
    }
}
