package com.sweet_smash.ecommerce.controllers;

import com.sweet_smash.ecommerce.dtos.CreateProductDto;
import com.sweet_smash.ecommerce.entities.Product;
import com.sweet_smash.ecommerce.responses.ResponseHandler;
import com.sweet_smash.ecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto) {
        try {
            Product product = productService.createProduct(createProductDto);
            return ResponseHandler.generateResponse(true, "All Ok", HttpStatus.CREATED, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseHandler.generateResponse(true, "All Ok", HttpStatus.OK, products);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductDetailsById(@PathVariable long productId) {
        try {
            Product product = productService.getProductDetailById(productId);
            return ResponseHandler.generateResponse(true, "All Ok", HttpStatus.OK, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
