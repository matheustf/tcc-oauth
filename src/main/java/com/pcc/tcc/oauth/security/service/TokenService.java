package com.pcc.tcc.oauth.security.service;

import com.pcc.tcc.oauth.dto.LoginDTO;
import com.pcc.tcc.oauth.dto.TokenDTO;
import com.pcc.tcc.oauth.exception.OAuthException;

public interface TokenService {

    String getToken(String username, String password) throws OAuthException;

	TokenDTO logar(LoginDTO login) throws OAuthException;
}
