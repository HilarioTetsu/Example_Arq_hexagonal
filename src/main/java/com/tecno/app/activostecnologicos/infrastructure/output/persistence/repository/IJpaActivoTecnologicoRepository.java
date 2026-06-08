package com.tecno.app.activostecnologicos.infrastructure.output.persistence.repository;

import com.tecno.app.activostecnologicos.domain.models.Estado;
import com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity.ActivoTecnologicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface IJpaActivoTecnologicoRepository extends JpaRepository<ActivoTecnologicoEntity, UUID> {

    String QUERY = "SELECT at FROM ActivoTecnologicoEntity at ";

    String FILTERS = """
            WHERE (:numSerie IS NULL OR at.numSerie = :numSerie)
            AND (:marca IS NULL OR at.marca=:marca)
            AND (:modelo IS NULL OR at.modelo=:modelo)
            AND (:costoMin IS NULL OR at.costo >= :costoMin)
            AND (:costoMax IS NULL OR at.costo <= :costoMax)
            AND (:estado IS NULL OR at.estado = :estado)
            AND (:categoriaId IS NULL OR at.categoria.id = :categoriaId)

            """;


    Optional<ActivoTecnologicoEntity> findByIdAndEstadoIsNot(UUID id, Estado estado);


    @Query(value = QUERY + FILTERS, countQuery = "SELECT COUNT(at) FROM ActivoTecnologicoEntity at")
    Page<ActivoTecnologicoEntity> searchActivoTecnologico(Pageable pageable,
                                                    @Param("numSerie") String numSerie,
                                                    @Param("marca") String marca,
                                                    @Param("modelo") String modelo,
                                                    @Param("costoMin") BigDecimal costoMin,
                                                    @Param("costoMax") BigDecimal costoMax,
                                                    @Param("estado") Estado estado,
                                                    @Param("categoriaId") Integer categoriaId);

    @Query(value = "SELECT nextval('num_seq') ;", nativeQuery = true)
    long obtenerSiguienteValorSecuencia();
}
