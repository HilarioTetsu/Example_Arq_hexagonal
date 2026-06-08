package com.tecno.app.activostecnologicos.domain.models;



import java.util.UUID;

public class Usuario {



    private final UUID id;
    private final String username;
    private String password;
    private Role role;


    public Usuario(UUID id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        setPassword(password);
        setRole(role);
    }

    public Usuario(String username, String password, Role role) {
        this.id = UUID.randomUUID();
        this.username = username;
        setPassword(password);
        setRole(role);
    }

    public void setPassword(String password) {


        if (password==null || password.trim().isEmpty())
            throw new IllegalArgumentException("La contraseña no puede estar vacia");

        this.password = password;
    }

    public void setRole(Role role) {
        if (role==null)
            throw new IllegalArgumentException("El rol no puede ser nulo");

        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
