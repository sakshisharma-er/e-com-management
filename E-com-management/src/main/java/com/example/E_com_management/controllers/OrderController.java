package com.example.E_com_management.controllers;

import com.example.E_com_management.services.OrderService;
import com.example.E_com_management.models.Order;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    private Order placeOrder(Authentication auth) {
        String email = auth.getName();
        return orderService.placeOrder(email);
    }
}
