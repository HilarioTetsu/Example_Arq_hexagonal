package com.tecno.app.activostecnologicos.infrastructure.input.controllers;

import com.tecno.app.activostecnologicos.application.ports.in.ICrearUsuarioPort;
import com.tecno.app.activostecnologicos.application.ports.in.dto.response.UsuarioResponse;
import com.tecno.app.activostecnologicos.infrastructure.input.dto.AuthRequestDto;
import com.tecno.app.activostecnologicos.infrastructure.input.dto.CrearUsuarioRequestDto;
import com.tecno.app.activostecnologicos.infrastructure.input.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
class AuthController {

    private final AuthenticationManager authManager;

    private final JwtUtil jwtUtil;

    private final ICrearUsuarioPort crearUsuarioPort;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Serializable>> login(@Valid @RequestBody AuthRequestDto request){



        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User principal = (User) Objects.requireNonNull(auth.getPrincipal(), "El principal de autenticación no debe ser nulo");

        String token = jwtUtil.generarToken(principal.getUsername(), principal.getAuthorities());

        return ResponseEntity.ok(Map.of("jwt", token, "expires", jwtUtil.getExpirationToken(token)));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponse> signUp(@Valid @RequestBody CrearUsuarioRequestDto  request){

        UsuarioResponse response = crearUsuarioPort.ejecutar(request.toCommand());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
