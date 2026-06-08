package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.IObtenerCategoriasPort;
import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.domain.models.Categoria;

import java.util.List;

public class ObtenerCategoriasUseCase implements IObtenerCategoriasPort {

    private final ICategoriaRepository repository;

    public ObtenerCategoriasUseCase(ICategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> ejecutar() {
        return repository.findAll();
    }
}
