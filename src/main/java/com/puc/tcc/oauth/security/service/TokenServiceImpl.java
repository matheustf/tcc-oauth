package com.puc.tcc.oauth.security.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.puc.tcc.oauth.consts.Constants;
import com.puc.tcc.oauth.dto.LoginDTO;
import com.puc.tcc.oauth.dto.TokenDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.model.Usuario;
import com.puc.tcc.oauth.utils.Util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	private static int tokenExpirationTime = 30;

	@Value("${security.token.secret.key}")
	private String tokenKey;

	private final UserDetailsService userDetailsService;
	
	@Autowired
	public TokenServiceImpl(final UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public TokenDTO logar(LoginDTO login) throws OAuthException {
		String token = getToken(login.getUsername(), login.getPassword());

		if (token == null) {
			throw new OAuthException(HttpStatus.BAD_REQUEST, Constants.BAD_REQUEST);
		}

		TokenDTO response = new TokenDTO();
		response.setToken(token);

		return response;
	}

	@Override
	public String getToken(final String username, String password) throws OAuthException {
		if (username == null || password == null) {
			return null;
		}
		final Usuario user = (Usuario) userDetailsService.loadUserByUsername(username);
		Map<String, Object> tokenData = new HashMap<>();
		
		password = Util.criptografarMD5(password);
		
		if (!password.equals(user.getPassword())) {
			throw new OAuthException(HttpStatus.FORBIDDEN, Constants.FORBIDDEN);
		}

		if(user.getIdCadastro() != null) {
			tokenData.put("idCadastro", user.getIdCadastro());
		}
		if(user.getIdCadastro() != null) {
			tokenData.put("email", user.getEmail());
		}
		tokenData.put("clientType", "user");
		tokenData.put("userID", user.getId());
		tokenData.put("username", user.getUsername());
		tokenData.put("token_create_date", LocalDateTime.now());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, tokenExpirationTime);
		tokenData.put("token_expiration_date", calendar.getTime());
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setExpiration(calendar.getTime());
		jwtBuilder.setClaims(tokenData);

		return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
	}

	public static void setTokenExpirationTime(final int tokenExpirationTime) {
		TokenServiceImpl.tokenExpirationTime = tokenExpirationTime;
	}
}
