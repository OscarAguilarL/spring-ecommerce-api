package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.SignUpDto;
import com.sweet_smash.ecommerce.entities.Role;
import com.sweet_smash.ecommerce.entities.User;
import com.sweet_smash.ecommerce.repositories.RoleRepository;
import com.sweet_smash.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDto signUpDto) {

        if (!signUpDto.getPassword().equals(signUpDto.getPasswordConfirmation())) {
            return new ResponseEntity<>("Passwords does not match", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email already taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User(
                signUpDto.getName(),
                signUpDto.getLastname(),
                signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword())
        );

        Role roles = roleRepository.findByName("ROLE_ADMIN").orElse(null);
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
