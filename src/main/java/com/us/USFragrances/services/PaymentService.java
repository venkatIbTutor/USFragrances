package com.us.USFragrances.services;

import com.us.USFragrances.models.Order;
import com.us.USFragrances.models.Payment;
import com.us.USFragrances.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment processPayment(Order order, String method) {
        Payment payment = new Payment(null, order, method, "TXN-" + System.currentTimeMillis(), LocalDateTime.now(), true);
        return paymentRepository.save(payment);
    }
}