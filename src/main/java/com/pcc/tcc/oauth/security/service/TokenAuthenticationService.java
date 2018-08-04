package com.pcc.tcc.oauth.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.pcc.tcc.oauth.model.Usuario;


public interface TokenAuthenticationService {

    Authentication authenticate(HttpServletRequest request);
    
    public Usuario buscarLogin(String token);
    
}
