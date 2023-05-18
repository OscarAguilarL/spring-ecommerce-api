package com.sweet_smash.ecommerce.dtos;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String number;
    private String zipcode;
    private long userId;
}
