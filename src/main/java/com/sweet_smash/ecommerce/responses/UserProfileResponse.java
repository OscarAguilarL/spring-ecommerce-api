package com.sweet_smash.ecommerce.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileResponse {
    private long id;
    private String name;
    private String lastname;
    private String email;
}
