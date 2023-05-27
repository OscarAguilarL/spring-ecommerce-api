package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.responses.UserProfileResponse;
import com.sweet_smash.ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserProfile(@PathVariable long userId) {
        try {
            UserProfileResponse userProfileResponse = userService.getUserProfile(userId);
            return ResponseHandler.generateResponse(true, "All Ok",HttpStatus.OK, userProfileResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
