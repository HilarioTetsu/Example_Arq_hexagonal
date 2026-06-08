package com.tecno.app.activostecnologicos.infrastructure.input.dto;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.ActualizarActivoCommand;
import com.tecno.app.activostecnologicos.domain.models.Estado;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ActualizarActivoRequest(String numSerie, String marca, String modelo, @Positive BigDecimal costo, Estado estado, Integer categoriaId) {


    public ActualizarActivoCommand toCommand(UUID id){
        return new ActualizarActivoCommand(id,numSerie,marca,modelo,costo,estado,categoriaId);
    }

}
