package com.a09.a09.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateDto(@NotBlank String nome, @Positive Integer idade, @Positive Integer sala) {
}