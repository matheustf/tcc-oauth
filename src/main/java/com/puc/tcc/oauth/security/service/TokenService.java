package com.puc.tcc.oauth.security.service;

import com.puc.tcc.oauth.dto.LoginDTO;
import com.puc.tcc.oauth.dto.TokenDTO;
import com.puc.tcc.oauth.exception.OAuthException;

public interface TokenService {

    String getToken(String username, String password) throws OAuthException;

	TokenDTO logar(LoginDTO login) throws OAuthException;
}
