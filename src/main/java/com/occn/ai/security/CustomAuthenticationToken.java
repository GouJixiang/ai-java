package com.occn.ai.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 6339981734663827267L;

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
