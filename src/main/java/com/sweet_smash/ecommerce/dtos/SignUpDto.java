package com.sweet_smash.ecommerce.dtos;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String passwordConfirmation;
}
