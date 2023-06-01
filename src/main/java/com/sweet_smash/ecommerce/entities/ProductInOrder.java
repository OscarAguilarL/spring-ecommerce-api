package com.sweet_smash.ecommerce.entities;

import com.sweet_smash.ecommerce.keys.ProductInOrderKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_in_order")
public class ProductInOrder {

    @EmbeddedId
    private ProductInOrderKey id;

    private int quantity;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductInOrder(int quantity, Order order, Product product) {
        this.id = new ProductInOrderKey(order.getId(), product.getId());
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
