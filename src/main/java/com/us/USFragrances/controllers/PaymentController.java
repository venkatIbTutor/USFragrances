package com.us.USFragrances.controllers;

import com.us.USFragrances.models.Order;
import com.us.USFragrances.models.Payment;
import com.us.USFragrances.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestParam Long orderId, @RequestParam String method) {
        Order order = new Order(); // Fetch order in a real case
        return ResponseEntity.ok(paymentService.processPayment(order, method));
    }
}
