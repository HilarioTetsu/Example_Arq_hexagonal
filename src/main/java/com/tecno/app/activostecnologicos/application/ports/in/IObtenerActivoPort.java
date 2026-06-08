package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.application.ports.in.dto.query.ObtenerActivoFiltrosQuery;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IObtenerActivoPort {

    Page<ActivoResponse> ejecutar(ObtenerActivoFiltrosQuery query);

    ActivoResponse ejecutar(UUID id);
}
