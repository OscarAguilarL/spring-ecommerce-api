package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.SignUpDto;
import com.sweet_smash.ecommerce.entities.Role;
import com.sweet_smash.ecommerce.entities.User;
import com.sweet_smash.ecommerce.repositories.RoleRepository;
import com.sweet_smash.ecommerce.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(SignUpDto signUpDto) {
        if (!signUpDto.getPassword().equals(signUpDto.getPasswordConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords does not match");
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already taken");
        }

        User user = new User(
                signUpDto.getName(),
                signUpDto.getLastname(),
                signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword())
        );

        Role roles = roleRepository.findByName("ROLE_USER").orElse(null);
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
    }
}
