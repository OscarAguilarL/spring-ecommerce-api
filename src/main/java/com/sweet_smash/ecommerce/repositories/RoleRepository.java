package com.sweet_smash.ecommerce.repositories;

import com.sweet_smash.ecommerce.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
