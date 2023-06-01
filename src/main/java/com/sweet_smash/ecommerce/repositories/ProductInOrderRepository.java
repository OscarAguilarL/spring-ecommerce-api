package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.ProductInOrder;
import com.sweet_smash.ecommerce.keys.ProductInOrderKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderKey> {
}
