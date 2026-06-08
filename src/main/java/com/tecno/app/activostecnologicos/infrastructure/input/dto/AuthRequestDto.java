package com.tecno.app.activostecnologicos.infrastructure.input.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(@NotBlank String username, @NotBlank String password) {
}
