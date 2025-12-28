package com.example.E_com_management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.E_com_management.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
