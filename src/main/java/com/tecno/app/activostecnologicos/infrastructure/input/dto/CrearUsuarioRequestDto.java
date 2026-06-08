package com.tecno.app.activostecnologicos.infrastructure.input.dto;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearUsuarioCommand;
import com.tecno.app.activostecnologicos.domain.models.Role;
import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequestDto(@NotBlank String username,
                                     @NotBlank String password,
                                     Role role) {

    public CrearUsuarioCommand toCommand(){
        return new CrearUsuarioCommand(username,password,role);
    }

}
