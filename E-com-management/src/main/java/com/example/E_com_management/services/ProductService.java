package com.example.E_com_management.services;

import com.example.E_com_management.models.Product;
import com.example.E_com_management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setActive(true);
        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    public Product updateStock(String productId, int stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(stock);
        return productRepository.save(product);
    }
}
