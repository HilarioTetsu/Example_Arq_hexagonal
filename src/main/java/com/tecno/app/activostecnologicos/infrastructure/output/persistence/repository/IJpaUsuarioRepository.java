package com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository;

import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IJpaUsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {



    Optional<UsuarioEntity> findByUsername(String username);
}
