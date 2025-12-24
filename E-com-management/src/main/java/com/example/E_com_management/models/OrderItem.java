package com.example.E_com_management.models;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String productId;
    private String productName;
    private int quatity;
    private double price;
    
}
