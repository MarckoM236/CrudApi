package com.marco.crudapi.Product.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message="The name es obligatory.")
    String name,

    @NotNull(message="The price es obligatory.")
    @Positive
    Double price

){}


