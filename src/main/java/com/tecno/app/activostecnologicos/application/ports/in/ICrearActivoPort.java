package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;

public interface ICrearActivoPort {

    ActivoResponse ejecutar(CrearActivoCommand command);
}
