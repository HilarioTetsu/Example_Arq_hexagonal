package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.domain.models.Usuario;

public interface IObtenerUsuarioPorUsername {

    Usuario ejecutar(String username);

}
