package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.UserProfileDto;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.responses.UserProfileResponse;
import com.sweet_smash.ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getUserProfile(@RequestBody UserProfileDto userProfileDto) {
        try {
            UserProfileResponse userProfileResponse = userService.getUserProfile(userProfileDto.getEmail());
            return ResponseHandler.generateResponse(true, "All Ok",HttpStatus.OK, userProfileResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
