package com.backend.atividades.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record TransacaoDto(
        @NotBlank String recebedor,
        @NotBlank String pagador,
        @DecimalMin(value = "0.00", inclusive = false) @Digits(integer = 20, fraction = 2) Double quantidade) {
}
