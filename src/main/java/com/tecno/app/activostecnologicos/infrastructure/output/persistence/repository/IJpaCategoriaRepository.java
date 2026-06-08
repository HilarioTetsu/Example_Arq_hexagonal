package com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository;

import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaCategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
}
