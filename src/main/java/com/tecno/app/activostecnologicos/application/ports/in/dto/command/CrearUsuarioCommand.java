package com.tecno.app.activostecnologicos.application.ports.in.dto.command;

import com.tecno.app.activostecnologicos.domain.models.Role;



public record CrearUsuarioCommand( String username,
                                   String password,
                                     Role role) {


}
