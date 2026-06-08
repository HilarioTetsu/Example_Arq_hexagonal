package com.tecno.app.activostecnologicos.application.ports.out;

import com.tecno.app.activostecnologicos.domain.models.Usuario;

public interface IUsuarioRepository {

    void guardar(Usuario usuario);

    Usuario buscarPorUsername(String username);

}
