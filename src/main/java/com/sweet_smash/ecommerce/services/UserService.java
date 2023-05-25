package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.SignUpDto;
import com.sweet_smash.ecommerce.entities.Role;
import com.sweet_smash.ecommerce.entities.User;
import com.sweet_smash.ecommerce.repositories.RoleRepository;
import com.sweet_smash.ecommerce.repositories.UserRepository;
import com.sweet_smash.ecommerce.responses.UserProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

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

    public UserProfileResponse getUserProfile(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserProfileResponse responseUser = new UserProfileResponse();
        responseUser.setName(user.get().getName());
        responseUser.setEmail(user.get().getEmail());
        responseUser.setLastname(user.get().getLastname());
        responseUser.setId(user.get().getId());

        return responseUser;
    }
}
