package com.tecno.app.activostecnologicos.application.ports.out;

import com.tecno.app.activostecnologicos.application.ports.in.dto.response.PaginaResponse;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Estado;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface IActivoTecnologicoRepository {


    Optional<ActivoTecnologico> obtenerPorId(UUID id);

    PaginaResponse<ActivoTecnologico> buscarActivoTecnologico(
            int page, int size, String sorts, String numSerie, String marca,
            String modelo, BigDecimal costoMin, BigDecimal costoMax,
            Estado estado, Integer categoriaId
    );
    void guardar(ActivoTecnologico activo);

    int obtenerSiguienteNumeroSecuencial();

}
