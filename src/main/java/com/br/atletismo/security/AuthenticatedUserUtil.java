package com.br.atletismo.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUserUtil {

    public static String getAuthenticatedUsername() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return authentication.getPrincipal().toString();
        }

        throw new RuntimeException("Usuário não autenticado.");
    }

}
