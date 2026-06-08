package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearUsuarioCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.UsuarioResponse;

public interface ICrearUsuarioPort {


    UsuarioResponse ejecutar(CrearUsuarioCommand command);


}
