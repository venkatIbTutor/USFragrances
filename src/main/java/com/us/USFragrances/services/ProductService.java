package com.us.USFragrances.services;

import com.us.USFragrances.models.Product;
import com.us.USFragrances.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void addProduct(Product request) {
        productRepository.save(request);
    }
    @Transactional
    public void updateProduct(Long id, Product request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public long getProductCount() {
        return productRepository.count();
    }
}