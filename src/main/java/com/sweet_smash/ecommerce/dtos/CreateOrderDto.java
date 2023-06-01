package com.sweet_smash.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrderDto {

    private long userId;
    private long paymentTypeId;
    private List<ProductInOrderDto> productsInOrder;
}
