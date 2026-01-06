package com.example.E_com_management.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "Carts")
public class Cart {
    @Id
    private String id;
    private String userID;
    private List<CartItem> items;
    private double totalAmount;

}
