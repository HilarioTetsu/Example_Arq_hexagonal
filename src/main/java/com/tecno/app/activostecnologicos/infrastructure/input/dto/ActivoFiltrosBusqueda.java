package com.tecno.app.activostecnologicos.infrastructure.input.dto;

import com.tecno.app.activostecnologicos.application.ports.in.dto.query.ObtenerActivoFiltrosQuery;
import com.tecno.app.activostecnologicos.domain.models.Estado;

import java.math.BigDecimal;


public record ActivoFiltrosBusqueda(Integer page,
                                    Integer size,
                                    String numSerie,
                                    String marca,
                                    String modelo,
                                    BigDecimal costoMin,
                                    BigDecimal costoMax,
                                    Estado estado,
                                    Integer categoriaId,
                                    String sorts) {
    public ActivoFiltrosBusqueda {
        if (sorts == null || sorts.isBlank()) sorts = "fechaIngreso,desc;";
        if (size == null) size=10;
        if (page == null) page=0;
    }

    public ObtenerActivoFiltrosQuery toQuery(){
        return new ObtenerActivoFiltrosQuery(page,size,numSerie,marca,modelo,costoMin,costoMax,estado,categoriaId,sorts);
    }

}