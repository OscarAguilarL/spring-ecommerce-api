package com.sweet_smash.ecommerce.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
