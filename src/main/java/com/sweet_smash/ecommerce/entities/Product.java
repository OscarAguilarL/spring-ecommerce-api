package com.sweet_smash.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String label;
    private String description;
    private double unitPrice;
    @ElementCollection
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String label, String description, double unitPrice, List<String> images, Stock stock) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.unitPrice = unitPrice;
        this.images.addAll(images);
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
