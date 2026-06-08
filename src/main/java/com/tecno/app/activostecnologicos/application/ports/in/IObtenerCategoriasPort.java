package com.tecno.app.activostecnologicos.application.ports.in;

import com.tecno.app.activostecnologicos.domain.models.Categoria;

import java.util.List;

public interface IObtenerCategoriasPort {

    List<Categoria> ejecutar();
}
