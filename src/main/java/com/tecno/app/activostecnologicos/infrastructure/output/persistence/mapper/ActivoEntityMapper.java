package com.tecno.app.activostecnologicos.infrastructure.output.persistence.mapper;

import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Categoria;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.ActivoTecnologicoEntity;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.CategoriaEntity;

public class ActivoEntityMapper {

    public static ActivoTecnologico toDomain(ActivoTecnologicoEntity entity) {
        if (entity == null) {
            return null;
        }

        Categoria categoria = new Categoria(
                entity.getCategoria().getId(),
                entity.getCategoria().getNombre(),
                entity.getCategoria().getCode()
        );

        return new ActivoTecnologico(
                entity.getId(),
                entity.getFolio(),
                entity.getNumSerie(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getEstado(),
                entity.getCosto(),
                entity.getFechaIngreso(),
                categoria,
                entity.getNumero()
        );
    }

    public static ActivoTecnologicoEntity toEntity(ActivoTecnologico domain) {
        if (domain == null) {
            return null;
        }

        CategoriaEntity categoriaEntity = CategoriaEntity.builder()
                .id(domain.getCategoria().getId())
                .nombre(domain.getCategoria().getNombre())
                .code(domain.getCategoria().getCode())
                .build();

        return ActivoTecnologicoEntity.builder()
                .id(domain.getId())
                .folio(domain.getFolio())
                .numSerie(domain.getNumSerie())
                .marca(domain.getMarca())
                .modelo(domain.getModelo())
                .estado(domain.getEstado())
                .costo(domain.getCosto())
                .fechaIngreso(domain.getFechaIngreso())
                .categoria(categoriaEntity)
                .numero(domain.getNumero())
                .build();
    }
}
