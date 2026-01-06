package com.example.E_com_management.services;

import com.example.E_com_management.models.Cart;
import com.example.E_com_management.models.Product;
import com.example.E_com_management.models.CartItem;
import com.example.E_com_management.repository.CartRepository;
import com.example.E_com_management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
        private final CartRepository cartRepository;
        private final ProductRepository productRepository;

        public Cart addtoCart(String userId, String productId, int quanity) {
                Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new RuntimeException("Product not found"));

                Cart cart = cartRepository.findByUserID(userId)
                                .orElseGet(() -> Cart.builder()
                                                .userID(userId)
                                                .items(new ArrayList<>())
                                                .totalAmount(0)
                                                .build());
                CartItem item = new CartItem(productId, quanity, product.getPrice());
                cart.getItems().add(item);
                cart.setTotalAmount(
                                cart.getItems().stream()
                                                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                                                .sum());
                return cartRepository.save(cart);
        }
}
