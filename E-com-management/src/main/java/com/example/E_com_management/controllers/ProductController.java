package com.example.E_com_management.controllers;

import com.example.E_com_management.models.Product;
import com.example.E_com_management.services.ProductService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("path")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);

    }

    @GetMapping
    public List<Product> getAllActiveProducts() {
        return productService.getAllActiveProducts();
    }

    @PutMapping("/{id}/stock")
    public Product updateStock(@PathVariable String id, @RequestParam int stock) {
        return productService.updateStock(id, stock);
    }

}
