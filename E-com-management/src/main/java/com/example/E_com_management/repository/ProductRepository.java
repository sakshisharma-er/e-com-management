package com.example.E_com_management.repository;

import com.example.E_com_management.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryId(String CategoryId);

    List<Product> findByActiveTrue();

}
