package com.sweet_smash.ecommerce.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetails paymentDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(User user, PaymentDetails paymentDetails) {
        this.user = user;
        this.paymentDetails = paymentDetails;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
