package com.tecno.app.activostecnologicos.infrastructure.input.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class CustomUserDetails extends User {

    @Serial
    private static final long serialVersionUID = -5335551184248832877L;

    private UUID userId;



    public CustomUserDetails(String username, String password,Collection<? extends GrantedAuthority> authorities,
                             UUID userId) {
        super(username, password, true, true, true, true, authorities);
        this.userId = userId;
    }
}
