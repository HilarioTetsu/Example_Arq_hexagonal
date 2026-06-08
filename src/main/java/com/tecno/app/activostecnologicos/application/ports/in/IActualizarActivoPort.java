package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.ActualizarActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;

public interface IActualizarActivoPort {

    ActivoResponse ejecutar(ActualizarActivoCommand command);
}
