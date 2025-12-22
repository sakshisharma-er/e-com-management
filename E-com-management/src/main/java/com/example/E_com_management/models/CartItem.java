package com.example.E_com_management.models;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private String productId;
    private int quantity;
    private double price;
}
