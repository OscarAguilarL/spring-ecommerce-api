package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.CreateOrderDto;
import com.sweet_smash.ecommerce.entities.Order;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/request")
    public ResponseEntity<Object> requestForOrder(@RequestBody CreateOrderDto createOrderDto) {
        try {
            Order order = this.orderService.registerOrder(createOrderDto);
            return ResponseHandler.generateResponse(true, "All Ok", HttpStatus.CREATED, order);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
