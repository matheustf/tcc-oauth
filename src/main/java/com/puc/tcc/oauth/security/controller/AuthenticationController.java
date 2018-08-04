package com.puc.tcc.oauth.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.oauth.dto.LoginDTO;
import com.puc.tcc.oauth.dto.TokenDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.security.service.TokenService;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody final LoginDTO login) throws OAuthException {
    	TokenDTO token = tokenService.logar(login);
    	
    	return new ResponseEntity<TokenDTO>(token, HttpStatus.OK);
    }
}
