package com.tecno.app.activostecnologicos.infrastructure.output.persistence.adapter;


import com.tecno.app.activostecnologicos.application.ports.in.dto.response.PaginaResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Estado;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.ActivoTecnologicoEntity;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.mapper.ActivoEntityMapper;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository.IJpaActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ActivoTecnologicoPersistenceAdapter implements IActivoTecnologicoRepository {

    private final IJpaActivoTecnologicoRepository jpaActivoTecnologicoRepository;

    public ActivoTecnologicoPersistenceAdapter(IJpaActivoTecnologicoRepository jpaActivoTecnologicoRepository) {
        this.jpaActivoTecnologicoRepository = jpaActivoTecnologicoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoTecnologico> obtenerPorId(UUID id) {

        Optional<ActivoTecnologicoEntity> entity = jpaActivoTecnologicoRepository.findByIdAndEstadoIsNot(id, Estado.BAJA);

        if (entity.isEmpty()) return Optional.empty();

        ActivoTecnologico activo = ActivoEntityMapper.toDomain(entity.get());


        return Optional.of(activo);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginaResponse<ActivoTecnologico> buscarActivoTecnologico(
            int page, int size, String sorts, String numSerie, String marca,
            String modelo, BigDecimal costoMin, BigDecimal costoMax,
            Estado estado, Integer categoriaId) {

        // La infraestructura asume la responsabilidad de instanciar las lógicas de Spring Data
        Pageable pageable = PageRequest.of(page, size, Utils.parseSortParams(sorts));

        Page<ActivoTecnologicoEntity> pageEntity = jpaActivoTecnologicoRepository.searchActivoTecnologico(
                pageable, numSerie, marca, modelo, costoMin, costoMax, estado, categoriaId
        );

        List<ActivoTecnologico> contenidoDominio = pageEntity.getContent().stream()
                .map(ActivoEntityMapper::toDomain)
                .toList();

        // Mapeamos el Page de Spring al DTO agnóstico de salida
        return new PaginaResponse<>(
                contenidoDominio,
                pageEntity.getNumber(),
                pageEntity.getSize(),
                pageEntity.getTotalElements(),
                pageEntity.getTotalPages()
        );
    }

    @Override
    public void guardar(ActivoTecnologico activo) {

        ActivoTecnologicoEntity entity = ActivoEntityMapper.toEntity(activo);

        jpaActivoTecnologicoRepository.saveAndFlush(entity);

    }

    @Override
    public int obtenerSiguienteNumeroSecuencial() {
        return (int) jpaActivoTecnologicoRepository.obtenerSiguienteValorSecuencia();
    }
}
