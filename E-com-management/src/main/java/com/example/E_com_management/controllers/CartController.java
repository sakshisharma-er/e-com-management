package com.example.E_com_management.controllers;

import com.example.E_com_management.services.CartService;
import com.example.E_com_management.models.Cart;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(Authentication auth,
            @RequestParam String productId,
            @RequestParam int quantity) {
        String email = auth.getName();
        return cartService.addtoCart(email, productId, quantity);
    }
}
