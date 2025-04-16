package com.us.USFragrances.controllers;

import com.us.USFragrances.services.OrderService;
import com.us.USFragrances.services.ProductService;
import com.us.USFragrances.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // Restricts access to ADMIN users only
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userService.getUserCount());
        model.addAttribute("totalProducts", productService.getProductCount());
        model.addAttribute("ordersToday", orderService.getOrdersCountToday());
        return "admin-dashboard";
    }
}

