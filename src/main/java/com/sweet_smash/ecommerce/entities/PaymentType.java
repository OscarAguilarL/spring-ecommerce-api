package com.sweet_smash.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String label;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PaymentType(String name, String label) {
        this.name = name;
        this.label = label;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
