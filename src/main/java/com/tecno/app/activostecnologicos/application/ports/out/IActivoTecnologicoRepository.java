package com.tecno.app.activostecnologicos.application.ports.out;

import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IActivoTecnologicoRepository {


    Optional<ActivoTecnologico> obtenerPorId(UUID id);

    Page<ActivoTecnologico> buscarActivoTecnologico(Pageable pageable, String numSerie, String marca, String modelo, BigDecimal costoMin, BigDecimal costoMax, Estado estado, Integer categoriaId);

    void guardar(ActivoTecnologico activo);

    int obtenerSiguienteNumeroSecuencial();

}
