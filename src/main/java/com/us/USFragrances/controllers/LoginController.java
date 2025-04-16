package com.us.USFragrances.controllers;

import com.us.USFragrances.models.User;
import com.us.USFragrances.services.OtpService;
import com.us.USFragrances.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final OtpService otpService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (userService.validateUser(email, password)) {
            //userService.sendOtpToUser(email);
            redirectAttributes.addAttribute("email", email);
            return "redirect:/verify-otp"; // OTP verification screen
        }
        redirectAttributes.addAttribute("error", "Invalid Credentials");
        return "redirect:/login"; // back to login with error
    }


    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model) {
        if (otpService.verifyOtp(email, otp)) {
            otpService.removeOtp(email);
            User user = userService.findByEmail(email);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid otp.");
        return "redirect:verify-otp";
    }
    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String phone, Model model) {
        if (phone.length() == 10) {
            User user = userService.findByEmail(email);
            if (user.getId() == null) {
                userService.registerUser(email, password, phone);
                return "redirect:/auth/login"; // Redirect to login page
            }
        }
        model.addAttribute("error", "Invalid Details to register");
        return "register"; // Show the register page again with error
    }

}
