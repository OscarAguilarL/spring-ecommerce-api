package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    Boolean existsByEmail(String email);
}
