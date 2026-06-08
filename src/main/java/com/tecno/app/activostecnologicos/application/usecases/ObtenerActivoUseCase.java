package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.IObtenerActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.mapper.ActivoMapper;
import com.tecno.app.activostecnologicos.application.ports.in.dto.query.ObtenerActivoFiltrosQuery;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.application.utils.Utils;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


public class ObtenerActivoUseCase implements IObtenerActivoPort {

    private final IActivoTecnologicoRepository activoRepo;

    public ObtenerActivoUseCase(IActivoTecnologicoRepository activoRepo) {
        this.activoRepo = activoRepo;
    }

    @Override
    public Page<ActivoResponse> ejecutar(ObtenerActivoFiltrosQuery query) {

        Pageable pageable = PageRequest.of(query.page(), query.size(), Utils.parseSortParams(query.sorts()));

        Page<ActivoTecnologico> page = activoRepo.buscarActivoTecnologico(pageable, query.numSerie(), query.marca(), query.modelo(), query.costoMin(),query.costoMax(),query.estado(),query.categoriaId());

        return page.map(p -> ActivoMapper.toResponse(p));


    }

    @Override
    public ActivoResponse ejecutar(UUID id) {

        ActivoTecnologico activoTecnologico = activoRepo.obtenerPorId(id).orElseThrow(() -> new NoSuchElementException("Activo tecnologico no encontrado"));

        return ActivoMapper.toResponse(activoTecnologico);
    }
}
