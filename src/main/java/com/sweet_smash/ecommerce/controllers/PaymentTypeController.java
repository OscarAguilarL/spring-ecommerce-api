package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.PaymentTypeDto;
import com.sweet_smash.ecommerce.entities.PaymentType;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.services.PaymentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @PostMapping
    public ResponseEntity<Object> createPaymentType(@RequestBody PaymentTypeDto dto) {
        try {
            PaymentType paymentType = this.paymentTypeService.create(dto);
            return ResponseHandler.generateResponse(true, "All ok", HttpStatus.CREATED, paymentType);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllPaymentTypes() {
        try {
            List<PaymentType> paymentTypes = this.paymentTypeService.getAll();
            return ResponseHandler.generateResponse(true, "All ok", HttpStatus.OK, paymentTypes);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<Object> getPaymentTypeById(@PathVariable long paymentTypeId) {
        try {
            PaymentType paymentType = this.paymentTypeService.getById(paymentTypeId);
            return ResponseHandler.generateResponse(true, "All ok",HttpStatus.OK, paymentType);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
