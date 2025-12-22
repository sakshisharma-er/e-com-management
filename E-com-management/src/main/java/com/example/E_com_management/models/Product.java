package com.example.E_com_management.models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String categoryId;
    private boolean active;
}
