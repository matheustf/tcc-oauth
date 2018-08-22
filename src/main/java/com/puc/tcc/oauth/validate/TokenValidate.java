package com.puc.tcc.oauth.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.puc.tcc.oauth.consts.Constants;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.utils.Util;

@Component
public class TokenValidate {
	
	public void tokenSimpleValidate(String token) throws OAuthException {
		
		if(StringUtils.isBlank(token)) {
			new OAuthException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}

	}
	
	public void tokenValidate(String token) throws OAuthException {
		String idCadastro = Util.getPagameterToken(token, "idCadastro");
		
		if(StringUtils.isBlank(idCadastro)) {
			new OAuthException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}

	}

	public void tokenValidateCliente(String token, String idCliente) throws OAuthException {
		String idCadastro = Util.getPagameterToken(token, "idCadastro");
		
		if (!isTokenValido(idCadastro, idCliente)) {
			new OAuthException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}
	}

	private boolean isTokenValido(String idCadastro, String idCliente) {
		return StringUtils.isNotBlank(idCadastro) && idCadastro.equals(idCliente);
	}

}
