package com.tecno.app.activostecnologicos.infrastructure.input.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;



public record CrearActivoRequest(@NotBlank String numSerie,
                                 @NotBlank String marca,
                                 @NotBlank String modelo,
                                 @Positive BigDecimal costo,
                                 int categoriaId) {
}
