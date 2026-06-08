package com.tecno.app.activostecnologicos.infrastructure.output.persistence.adapter;


import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Estado;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.ActivoTecnologicoEntity;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.mapper.ActivoEntityMapper;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository.IJpaActivoTecnologicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public Page<ActivoTecnologico> buscarActivoTecnologico(Pageable pageable, String numSerie, String marca, String modelo, BigDecimal costoMin, BigDecimal costoMax, Estado estado, Integer categoriaId) {

        Page<ActivoTecnologicoEntity> page = jpaActivoTecnologicoRepository.searchActivoTecnologico(pageable, numSerie,  marca,  modelo,  costoMin,  costoMax,  estado,  categoriaId);

        return page.map(ActivoEntityMapper::toDomain);
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
