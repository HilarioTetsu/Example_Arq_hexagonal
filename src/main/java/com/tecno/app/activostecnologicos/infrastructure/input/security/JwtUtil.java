package com.tecno.app.activostecnologicos.infrastructure.input.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final long expMinutes;

    public JwtUtil(@Value("${app.jwt.secretKey}") String key, @Value("${app.jwt.expiration-minutes}") long expminutes) {
        this.key = Keys.hmacShaKeyFor(key.getBytes());
        this.expMinutes = expminutes;
    }

    public String generarToken(String subject,Collection<? extends GrantedAuthority> auths){

        String roles = auths.stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(","));

        Instant now = Instant.now();



        return Jwts.builder()
                .subject(subject)
                .claim("roles",roles)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(now.plus(expMinutes,ChronoUnit.MINUTES)))
                .signWith(key)
                .compact()
                ;
    }

    private Claims extraerClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extraerUsuario(String token) {
        return extraerClaims(token).getSubject();
    }

    public List<GrantedAuthority> extraerAutoridades(String token) {
        Claims claims = extraerClaims(token);
        String rolesString = claims.get("roles", String.class);

        if (rolesString == null || rolesString.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(rolesString.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Instant getExpirationToken(String token) {
        return extraerClaims(token).getExpiration().toInstant();
    }

    public boolean isTokenValido(String token) {
        try {
            extraerClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
