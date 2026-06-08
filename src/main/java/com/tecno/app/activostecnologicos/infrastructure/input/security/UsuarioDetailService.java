package com.tecno.app.activostecnologicos.infrastructure.input.security;

import com.tecno.app.activostecnologicos.application.ports.in.IObtenerUsuarioPorUsername;
import com.tecno.app.activostecnologicos.domain.models.Usuario;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDetailService implements UserDetailsService {

    private final IObtenerUsuarioPorUsername portUsername;

    public UsuarioDetailService(IObtenerUsuarioPorUsername portUsername) {
        this.portUsername = portUsername;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        Usuario usuario = portUsername.ejecutar(username);

        List<GrantedAuthority> roles = new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_"+usuario.getRole())));


        return new CustomUserDetails(usuario.getUsername(),usuario.getPassword(),roles,usuario.getId());
    }


}
