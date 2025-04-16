package com.us.USFragrances.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private EmailService emailService;

    public void sendOrderUpdateNotification(String email, String message) {
        System.out.println("📢 Notification sent to " + email + ": " + message);
        emailService.sendNotificationEmail(email,"Thank you for your Order.",message);
        // Future Implementation: Use Email Service or WebSockets for real-time updates
    }
}
