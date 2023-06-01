package com.sweet_smash.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductInOrderDto {

    private int quantity;
    private long productId;
}
