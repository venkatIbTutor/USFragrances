package com.us.USFragrances.controllers;

import com.us.USFragrances.models.Order;
import com.us.USFragrances.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody Order orderRequest) {
        orderService.processOrder(orderRequest);
        return ResponseEntity.ok("Order Placed Successfully");
    }
}
