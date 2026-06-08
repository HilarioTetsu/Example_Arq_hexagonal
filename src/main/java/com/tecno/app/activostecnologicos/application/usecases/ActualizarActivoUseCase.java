package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.IActualizarActivoPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.command.ActualizarActivoCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.mapper.ActivoMapper;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.ActivoResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.domain.models.ActivoTecnologico;
import com.tecno.app.activostecnologicos.domain.models.Categoria;

import java.util.NoSuchElementException;

public class ActualizarActivoUseCase implements IActualizarActivoPort {

    private final IActivoTecnologicoRepository activoRepo;
    private final ICategoriaRepository categoriaRepository;

    public ActualizarActivoUseCase(IActivoTecnologicoRepository activoRepo, ICategoriaRepository categoriaRepository) {
        this.activoRepo = activoRepo;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ActivoResponse ejecutar(ActualizarActivoCommand command) {

        ActivoTecnologico activoTecnologico = activoRepo.obtenerPorId(command.id()).orElseThrow(()
                -> new NoSuchElementException("Activo tecnologico no encontrado"));

        if (command.categoriaId() != null && !command.categoriaId().equals(activoTecnologico.getCategoria().getId())) {
            Categoria categoria = categoriaRepository.buscarPorId(command.categoriaId())
                    .orElseThrow(() -> new NoSuchElementException("Categoria no encontrada"));
            activoTecnologico.cambiarCategoria(categoria);
        }

        if (command.marca() != null && !command.marca().equalsIgnoreCase(activoTecnologico.getMarca())) {
            activoTecnologico.setMarca(command.marca());
        }
        if (command.modelo() != null && !command.modelo().equalsIgnoreCase(activoTecnologico.getModelo())) {
            activoTecnologico.setModelo(command.modelo());
        }
        if (command.numSerie() != null && !command.numSerie().equalsIgnoreCase(activoTecnologico.getNumSerie())) {
            activoTecnologico.setNumSerie(command.numSerie());
        }
        if (command.costo() != null && command.costo().compareTo(activoTecnologico.getCosto()) != 0) {
            activoTecnologico.setCosto(command.costo());
        }
        if (command.estado() != null && command.estado() != activoTecnologico.getEstado()) {
            activoTecnologico.cambiarEstado(command.estado());
        }


        activoRepo.guardar(activoTecnologico);

        return ActivoMapper.toResponse(activoTecnologico);
    }
}
