package com.tecno.app.activostecnologicos.infrastructure.config;

import com.tecno.app.activostecnologicos.application.ports.in.*;
import com.tecno.app.activostecnologicos.application.ports.out.IActivoTecnologicoRepository;
import com.tecno.app.activostecnologicos.application.ports.out.ICategoriaRepository;
import com.tecno.app.activostecnologicos.application.ports.out.IUsuarioRepository;
import com.tecno.app.activostecnologicos.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public ICrearActivoPort crearActivoPort(IActivoTecnologicoRepository activoRepo, ICategoriaRepository categoriaRepo) {
        return new CrearActivoUseCase(activoRepo, categoriaRepo);
    }

    @Bean
    public IObtenerActivoPort obtenerActivoPort(IActivoTecnologicoRepository activoRepo) {
        return new ObtenerActivoUseCase(activoRepo);
    }

    @Bean
    public IActualizarActivoPort actualizarActivoPort(IActivoTecnologicoRepository activoRepo, ICategoriaRepository categoriaRepo) {
        return new ActualizarActivoUseCase(activoRepo, categoriaRepo);
    }

    @Bean
    public IObtenerCategoriasPort obtenerCategoriasPort(ICategoriaRepository categoriaRepo){

        return new ObtenerCategoriasUseCase(categoriaRepo);
    }

    @Bean
    public ICrearUsuarioPort crearUsuarioPort(IUsuarioRepository usuarioRepository){

        return new CrearUsuarioUseCase(usuarioRepository);
    }

    @Bean
    public IObtenerUsuarioPorUsername obtenerUsuarioPorUsername(IUsuarioRepository usuarioRepository){

        return new ObtenerUsuarioPorUsernameUseCase(usuarioRepository);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
