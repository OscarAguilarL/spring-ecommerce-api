package com.sweet_smash.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDetailsDto {

    private double amount;
    private boolean status;
    private long paymentTypeId;
}
