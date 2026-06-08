package com.tecno.app.activostecnologicos.application.ports.in.dto.query;

import com.tecno.app.activostecnologicos.domain.models.Estado;

import java.math.BigDecimal;

public record ObtenerActivoFiltrosQuery(int page,int size,String numSerie, String marca, String modelo, BigDecimal costoMin,BigDecimal costoMax, Estado estado, Integer categoriaId,String sorts) {
}
