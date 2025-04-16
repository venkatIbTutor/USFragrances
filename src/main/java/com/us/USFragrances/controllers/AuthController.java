package com.us.USFragrances.controllers;

import com.us.USFragrances.services.OtpService;
import com.us.USFragrances.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Ensure "login.html" or "login.jsp" exists in templates/views
    }
    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }
    @GetMapping("/verify-otp")
    public String showVerifyPage(){
        return "verify-otp";
    }
}