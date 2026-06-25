package com.marco.crudapi.Auth.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUserAuth(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {
    
}
