package com.marco.crudapi.User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUserDto(
    @NotBlank(message="The name is obligatory.")
    String name,

    @NotBlank(message="The email is obligatory.")
    @Email(message = "The email is not valid")
    String email,

    @NotBlank(message="The password is obligatory.")
    String password
) {}
