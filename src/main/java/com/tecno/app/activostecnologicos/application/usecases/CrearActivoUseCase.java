package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.ICrearActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.mapper.ActivoMapper;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Categoria;

public class CrearActivoUseCase implements ICrearActivoPort {

    private final IActivoTecnologicoRepository activoRepo;
    private final ICategoriaRepository categoriaRepository;

    public CrearActivoUseCase(IActivoTecnologicoRepository activoRepo, ICategoriaRepository categoriaRepository) {
        this.activoRepo = activoRepo;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ActivoResponse ejecutar(CrearActivoCommand command) {

        Categoria categoria = categoriaRepository.buscarPorId(command.categoriaId()).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));

        int siguienteNumero = activoRepo.obtenerSiguienteNumeroSecuencial();

        ActivoTecnologico activoTecnologico = ActivoMapper.toDomain(command, categoria,siguienteNumero);

        activoRepo.guardar(activoTecnologico);

        return ActivoMapper.toResponse(activoTecnologico);
    }
}
