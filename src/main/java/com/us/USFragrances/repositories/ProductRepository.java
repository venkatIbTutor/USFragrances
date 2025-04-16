package com.us.USFragrances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.us.USFragrances.models.Product; // Ensure this is the correct package for your Product class

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
