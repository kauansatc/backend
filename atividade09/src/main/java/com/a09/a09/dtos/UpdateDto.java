package com.a09.a09.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateDto(
        @NotBlank String nome,
        @Positive @Nullable Integer idade,
        @Positive @Nullable Integer sala) {

}
