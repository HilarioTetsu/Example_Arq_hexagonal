package com.tecno.app.activostecnologicos.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false,length = 3,unique = true)
    private String code;

    @OneToMany(mappedBy = "categoria")
    private List<ActivoTecnologicoEntity> activos = new ArrayList<>();

}
