package com.tecno.app.activostecnologicos.application.ports.in.dto.command;

import com.tecno.app.activostecnologicos.domain.models.Estado;

import java.math.BigDecimal;
import java.util.UUID;

public record ActualizarActivoCommand(UUID id , String numSerie, String marca, String modelo, BigDecimal costo, Estado estado, Integer categoriaId) {
}
