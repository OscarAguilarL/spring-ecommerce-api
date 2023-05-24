package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.AddressDto;
import com.sweet_smash.ecommerce.entities.Address;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserAddress(@PathVariable Long userId) {
        try {
            Address address = addressService.getUserAddress(userId);
            return ResponseHandler.generateResponse(true, "All ok", HttpStatus.OK, address);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody AddressDto addressDto) {
        try {
            Address address = addressService.createAddress(addressDto);
            return ResponseHandler.generateResponse(true, "All ok", HttpStatus.CREATED, address);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
