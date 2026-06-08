package com.tecno.app.activostecnologicos.application.ports.out;

import com.tecno.app.activostecnologicos.domain.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository {

    Optional<Categoria> buscarPorId(Integer id);

    List<Categoria> findAll();

}
