package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

    Optional<PaymentType> findById(long id);

    Optional<PaymentType> findByName(String name);
}
