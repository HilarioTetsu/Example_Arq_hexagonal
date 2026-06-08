package com.tecno.app.activostecnologicos.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public class ActivoTecnologico {

    private final UUID id;
    private String folio;
    private String numSerie;
    private String marca;
    private String modelo;
    private Estado estado;
    private BigDecimal costo;
    private final LocalDateTime fechaIngreso;
    private Categoria categoria;
    private final int numero;
    private final String FORMATO_FOLIO="%s-%d-%03d";

    public ActivoTecnologico(String numSerie, String marca, String modelo, BigDecimal costo, Categoria categoria, int numero) {

        if (categoria == null) throw new IllegalArgumentException("La categoria no puede ser nula");

        this.id = UUID.randomUUID();
        setNumSerie(numSerie);
        setMarca(marca);
        setModelo(modelo);
        this.estado = Estado.DISPONIBLE;
        setCosto(costo);
        this.fechaIngreso = LocalDateTime.now();
        this.numero = numero;
        this.categoria = categoria;
        asignarFolio();
    }

    public ActivoTecnologico(UUID id, String folio, String numSerie, String marca, String modelo,
                             Estado estado, BigDecimal costo, LocalDateTime fechaIngreso, Categoria categoria, int numero) {
        this.id = id;
        this.folio = folio;
        setNumSerie(numSerie);
        setMarca(marca);
        setModelo(modelo);
        setCosto(costo);
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.categoria = categoria;
        this.numero = numero;
    }



    public void asignarFolio(){
        this.folio=String.format(FORMATO_FOLIO,this.categoria.getCode(),fechaIngreso.getYear(),this.numero);
    }

    public void cambiarCategoria(Categoria nuevaCategoria) {
        if (nuevaCategoria == null) throw new IllegalArgumentException("La categoria no puede ser nula");

        if (this.estado == Estado.BAJA) throw new IllegalStateException("No se puede cambiar la categoria de un activo dado de baja");

        this.categoria = nuevaCategoria;
        asignarFolio();
    }

    public void cambiarEstado(Estado estado){

        if (this.estado==Estado.BAJA)
            throw new IllegalStateException("El activo ya esta dado de baja");

        this.estado = estado;

    }

    public void setNumSerie(String numSerie) {
        if (numSerie == null || numSerie.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de serie no puede estar vacío");
        }
        if (this.estado == Estado.BAJA) {
            throw new IllegalStateException("No se puede modificar un activo dado de baja");
        }
        this.numSerie = numSerie;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }
        if (this.estado == Estado.BAJA) {
            throw new IllegalStateException("No se puede modificar un activo dado de baja");
        }
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío");
        }
        if (this.estado == Estado.BAJA) {
            throw new IllegalStateException("No se puede modificar un activo dado de baja");
        }
        this.modelo = modelo;
    }

    public void setCosto(BigDecimal costo) {
        if (costo == null || costo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El costo no puede ser nulo ni negativo");
        }
        if (this.estado == Estado.BAJA) {
            throw new IllegalStateException("No se puede modificar un activo dado de baja");
        }
        this.costo = costo;
    }

    public UUID getId() {
        return id;
    }

    public String getFolio() {
        return folio;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Estado getEstado() {
        return estado;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getNumero() {
        return numero;
    }
}
