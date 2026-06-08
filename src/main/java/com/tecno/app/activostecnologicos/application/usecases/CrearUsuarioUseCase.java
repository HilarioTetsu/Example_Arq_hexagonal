package com.tecno.app.activostecnologicos.application.usecases;

import com.tecno.app.activostecnologicos.application.ports.in.ICrearUsuarioPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.command.CrearUsuarioCommand;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.UsuarioResponse;
import com.tecno.app.activostecnologicos.application.ports.out.IPasswordHasher;
import com.tecno.app.activostecnologicos.application.ports.out.IUsuarioRepository;
import com.tecno.app.activostecnologicos.domain.models.Usuario;

public class CrearUsuarioUseCase implements ICrearUsuarioPort {


    private final IUsuarioRepository repository;
    private final IPasswordHasher passwordHasher; // Dependencia de abstracción pura

    public CrearUsuarioUseCase(IUsuarioRepository repository, IPasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public UsuarioResponse ejecutar(CrearUsuarioCommand command) {

        String passwordCifrado = passwordHasher.hash(command.password());

        Usuario usuario = new Usuario(command.username(), passwordCifrado, command.role());

        repository.guardar(usuario);

        return new UsuarioResponse(usuario.getUsername(), usuario.getRole());
    }
}
