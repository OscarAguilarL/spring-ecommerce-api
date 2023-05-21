package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.SignUpDto;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDto signUpDto) {
        try {
            userService.register(signUpDto);
            return ResponseHandler.generateResponse(true, "User registered successfully", HttpStatus.CREATED, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
