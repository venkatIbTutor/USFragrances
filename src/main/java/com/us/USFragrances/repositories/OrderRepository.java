package com.us.USFragrances.repositories;

import com.us.USFragrances.models.Order;
import com.us.USFragrances.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    long countByOrderDate(LocalDate now);
}
