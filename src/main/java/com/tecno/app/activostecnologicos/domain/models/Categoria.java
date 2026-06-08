package com.tecno.app.activostecnologicos.domain.models;

public class Categoria {

    private final Integer id;

    private final String nombre;

    private final String code;


    public Categoria(Integer id, String nombre, String code) {
        this.id = id;
        this.nombre = validarNombre(nombre);
        this.code = validarCode(code);
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNombre() {
        return nombre;
    }

    private String validarCode(String code) {

        if (code == null || code.isBlank() || code.length()!=3) {
            throw new IllegalArgumentException("Codigo de categoria no valido");
        }
        return code;
    }

    private String validarNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre de categoria no valido");
        }
        return nombre.trim();
    }



}
