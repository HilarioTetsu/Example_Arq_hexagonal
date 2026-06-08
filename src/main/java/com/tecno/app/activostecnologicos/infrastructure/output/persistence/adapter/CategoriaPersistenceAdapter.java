package com.tecno.app.activostecnologicos.infrastructure.output.persistence.adapter;

import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.domain.models.Categoria;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.CategoriaEntity;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository.IJpaCategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaPersistenceAdapter implements ICategoriaRepository {


    private final IJpaCategoriaRepository jpaCategoriaRepo;

    public CategoriaPersistenceAdapter(IJpaCategoriaRepository jpaCategoriaRepo) {
        this.jpaCategoriaRepo = jpaCategoriaRepo;
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {

        Optional<CategoriaEntity> entity = jpaCategoriaRepo.findById(id);

        if (entity.isEmpty()) return Optional.empty();

        Categoria domain = new Categoria(entity.get().getId(),entity.get().getNombre(),entity.get().getCode());

        return Optional.of(domain);
    }

    @Override
    public List<Categoria> findAll() {
        return jpaCategoriaRepo.findAll().stream().map(c -> new Categoria(c.getId(),c.getNombre(),c.getCode())).toList();
    }
}
