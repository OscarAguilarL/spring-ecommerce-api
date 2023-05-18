package com.sweet_smash.ecommerce.security;

import lombok.Data;

@Data
public class AuthCredentials {

    private String email;
    private String password;
}
