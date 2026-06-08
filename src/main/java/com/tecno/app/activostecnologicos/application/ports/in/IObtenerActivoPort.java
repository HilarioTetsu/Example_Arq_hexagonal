package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.application.ports.in.dto.query.ObtenerActivoFiltrosQuery;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.PaginaResponse;
import java.util.UUID;

public interface IObtenerActivoPort {

    PaginaResponse<ActivoResponse> ejecutar(ObtenerActivoFiltrosQuery query);

    ActivoResponse ejecutar(UUID id);
}
