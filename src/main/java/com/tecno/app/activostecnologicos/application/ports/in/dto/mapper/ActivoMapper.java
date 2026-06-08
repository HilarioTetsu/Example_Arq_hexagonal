package com.tecno.app.activostecnologicos.application.ports.in.dto.mapper;

import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Categoria;

public class ActivoMapper {

    public static ActivoResponse toResponse(ActivoTecnologico activo) {
        if (activo == null) {
            return null;
        }
        return new ActivoResponse(
                activo.getId(),
                activo.getFolio(),
                activo.getNumSerie(),
                activo.getMarca(),
                activo.getModelo(),
                activo.getEstado().name(),
                activo.getCosto(),
                activo.getFechaIngreso(),
                activo.getCategoria().getNombre()
        );
    }

    public static ActivoTecnologico toDomain(CrearActivoCommand command, Categoria categoria,int numeroSec) {
        if (command == null) {
            return null;
        }
        return new ActivoTecnologico(
                command.numSerie(),
                command.marca(),
                command.modelo(),
                command.costo(),
                categoria,
                numeroSec
        );
    }
}
