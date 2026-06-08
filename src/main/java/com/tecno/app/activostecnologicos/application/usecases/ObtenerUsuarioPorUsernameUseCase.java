package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.IObtenerUsuarioPorUsername;
import com.tecno.app.activostecnologicos.application.ports.out.IUsuarioRepository;
import com.tecno.app.activostecnologicos.domain.models.Usuario;

public class ObtenerUsuarioPorUsernameUseCase implements IObtenerUsuarioPorUsername {

    private final IUsuarioRepository repository;

    public ObtenerUsuarioPorUsernameUseCase(IUsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Usuario ejecutar(String username) {
        return repository.buscarPorUsername(username);
    }
}
