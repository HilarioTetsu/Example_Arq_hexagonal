package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.IObtenerActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.mapper.ActivoMapper;
import com.tecno.app.activostecnologicos.application.ports.in.dto.query.ObtenerActivoFiltrosQuery;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.PaginaResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


public class ObtenerActivoUseCase implements IObtenerActivoPort {

    private final IActivoTecnologicoRepository activoRepo;

    public ObtenerActivoUseCase(IActivoTecnologicoRepository activoRepo) {
        this.activoRepo = activoRepo;
    }

    @Override
    public PaginaResponse<ActivoResponse> ejecutar(ObtenerActivoFiltrosQuery query) {

        PaginaResponse<ActivoTecnologico> paginaDominio = activoRepo.buscarActivoTecnologico(
                query.page(), query.size(), query.sorts(), query.numSerie(), query.marca(),
                query.modelo(), query.costoMin(), query.costoMax(), query.estado(), query.categoriaId()
        );

        List<ActivoResponse> contenidoMapeado = paginaDominio.contenido().stream()
                .map(ActivoMapper::toResponse)
                .toList();

        return new PaginaResponse<>(
                contenidoMapeado,
                paginaDominio.numeroPagina(),
                paginaDominio.tamano(),
                paginaDominio.totalElementos(),
                paginaDominio.totalPaginas()
        );
    }

    @Override
    public ActivoResponse ejecutar(UUID id) {

        ActivoTecnologico activoTecnologico = activoRepo.obtenerPorId(id).orElseThrow(() -> new NoSuchElementException("Activo tecnologico no encontrado"));

        return ActivoMapper.toResponse(activoTecnologico);
    }
}
