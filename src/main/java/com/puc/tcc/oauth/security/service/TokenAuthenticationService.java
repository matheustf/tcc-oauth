package com.puc.tcc.oauth.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.puc.tcc.oauth.model.Usuario;


public interface TokenAuthenticationService {

    Authentication authenticate(HttpServletRequest request);
    
    public Usuario buscarLogin(String token);
    
}
