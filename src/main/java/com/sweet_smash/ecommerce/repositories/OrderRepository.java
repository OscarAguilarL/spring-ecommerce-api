package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
