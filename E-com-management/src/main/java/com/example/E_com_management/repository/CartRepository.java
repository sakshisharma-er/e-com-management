package com.example.E_com_management.repository;

import com.example.E_com_management.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserID(String userId);

}
