package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(long id);
}
