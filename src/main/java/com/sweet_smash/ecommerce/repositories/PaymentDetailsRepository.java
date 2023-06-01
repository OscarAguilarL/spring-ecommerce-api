package com.sweet_smash.ecommerce.repositories;


import com.sweet_smash.ecommerce.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
}
