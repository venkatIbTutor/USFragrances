package com.us.USFragrances.services;

import com.us.USFragrances.models.Order;
import com.us.USFragrances.models.OrderStatus;
import com.us.USFragrances.models.Payment;
import com.us.USFragrances.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    // Fetch all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Fetch pending orders
    public List<Order> getPendingOrders() {
        return orderRepository.findByStatus(OrderStatus.PENDING);
    }

    // Fetch order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Update order status
    public Order updateOrderStatus(Long id, OrderStatus status) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order Not Found!"));
    }

    // Process order
    @Transactional
    public void processOrder(Order orderRequest) {
        // 1. Set initial order status as PENDING and save
        orderRequest.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(orderRequest);

        // 2. Process payment (default method: CARD)
        Payment payment = paymentService.processPayment(savedOrder, "CARD");

        // 3. If payment is successful, update order status to CONFIRMED
        if (payment.isSuccess()) {
            savedOrder.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(savedOrder);
        } else {
            throw new RuntimeException("Payment failed! Order not processed.");
        }
    }
    public long getOrdersCountToday() {
        return orderRepository.countByOrderDate(LocalDate.now());
    }
}
