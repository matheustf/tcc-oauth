package com.pcc.tcc.oauth.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pcc.tcc.oauth.exception.UserNotFoundException;
import com.pcc.tcc.oauth.model.UserAuthentication;
import com.pcc.tcc.oauth.model.Usuario;
import com.pcc.tcc.oauth.security.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("${security.token.secret.key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;

    @Autowired
    public TokenAuthenticationServiceImpl(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(SecurityConstants.AUTH_HEADER_NAME);
        final Jws<Claims> tokenData = parseToken(token);
        if (tokenData != null) {
            Usuario user = getUserFromToken(tokenData);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    private Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private Usuario getUserFromToken(final Jws<Claims> tokenData) {
        try {
            return (Usuario) userDetailsService
                    .loadUserByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User "
                    + tokenData.getBody().get("username").toString() + " not found");
        }
    }
    
    @Override
    public Usuario buscarLogin(String token) {
    	final Jws<Claims> tokenData = parseToken(token);
        return getUserFromToken(tokenData);
    }
}
