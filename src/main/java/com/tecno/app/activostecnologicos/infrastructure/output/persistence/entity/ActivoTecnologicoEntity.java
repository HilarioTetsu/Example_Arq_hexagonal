package com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity;

import com.tecno.app.activostecnologicos.domain.models.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activos_tecnologicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivoTecnologicoEntity {

    @Id
    @Column(length = 36)
    private  UUID id;

    @Column(nullable = false)
    private String folio;

    @Column(nullable = false,unique = true)
    private String numSerie;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal costo;

    @Column(nullable = false)
    private LocalDateTime fechaIngreso;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @Column(nullable = false)
    private int numero;

}
