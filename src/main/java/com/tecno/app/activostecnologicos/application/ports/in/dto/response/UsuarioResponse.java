package com.tecno.app.activostecnologicos.application.ports.in.dto.response;

import com.tecno.app.activostecnologicos.domain.models.Role;

public record UsuarioResponse(String username, Role role) {
}
