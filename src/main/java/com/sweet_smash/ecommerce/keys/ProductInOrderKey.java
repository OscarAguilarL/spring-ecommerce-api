package com.sweet_smash.ecommerce.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ProductInOrderKey implements Serializable {

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;
}
