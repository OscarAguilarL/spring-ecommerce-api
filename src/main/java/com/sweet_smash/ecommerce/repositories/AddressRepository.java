package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.id=?1")
    Address findAddressByUserId(long userId);
}
