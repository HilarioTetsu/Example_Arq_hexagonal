package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.ICrearUsuarioPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearUsuarioCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.UsuarioResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IUsuarioRepository;
import com.tecno.app.activostecnologicos.domain.models.Usuario;

public class CrearUsuarioUseCase implements ICrearUsuarioPort {

    private final IUsuarioRepository repository;

    public CrearUsuarioUseCase(IUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioResponse ejecutar(CrearUsuarioCommand command) {

        Usuario usuario = new Usuario(command.username(),command.password(),command.role());

        repository.guardar(usuario);

        return new UsuarioResponse(usuario.getUsername(),usuario.getRole());
    }
}
