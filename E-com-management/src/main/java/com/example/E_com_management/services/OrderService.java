package com.example.E_com_management.services;

import com.example.E_com_management.enums.OrderStatus;
import com.example.E_com_management.models.*;
import com.example.E_com_management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public Order placeOrder(String userId) {
        Cart cart = cartRepository.findByUserID(userId).orElseThrow(() -> new RuntimeException("Cart is Empty"));
        Order order = Order.builder()
                .userId(userId)
                .items(
                        cart.getItems().stream()
                                .map(i -> new OrderItem(i.getProductId(), "Product", i.getQuantity(), i.getPrice()))
                                .collect(Collectors.toList()))
                .totalAmount(cart.getTotalAmount())
                .status(OrderStatus.PLACED)
                .orderDate(LocalDateTime.now())
                .build();
        cartRepository.delete(cart);
        orderRepository.save(order);
        return order;
    }
}
