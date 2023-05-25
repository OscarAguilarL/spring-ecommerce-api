package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByName(String name);
}
