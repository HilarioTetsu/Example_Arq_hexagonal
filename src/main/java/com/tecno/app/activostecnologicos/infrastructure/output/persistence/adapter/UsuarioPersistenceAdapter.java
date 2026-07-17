package com.tecno.app.activostecnologicos.infrastructure.output.persistence.adapter;

import com.tecno.app.activostecnologicos.application.ports.out.IUsuarioRepository;
import com.tecno.app.activostecnologicos.domain.models.Usuario;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.UsuarioEntity;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository.IJpaUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class UsuarioPersistenceAdapter implements IUsuarioRepository {

    private final IJpaUsuarioRepository repository;



    public UsuarioPersistenceAdapter(IJpaUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Usuario usuario) {

    UsuarioEntity entity = UsuarioEntity.builder()
            .id(usuario.getId())
            .role(usuario.getRole())
            .password(usuario.getPassword())
            .username(usuario.getUsername())
            .build();

        repository.save(entity);

    }

    @Override
    public Usuario buscarPorUsername(String username) {

        UsuarioEntity entity = repository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("No se encontro el usuario"));

        return new Usuario(entity.getId(),entity.getUsername(),entity.getPassword(),entity.getRole());
    }
}
