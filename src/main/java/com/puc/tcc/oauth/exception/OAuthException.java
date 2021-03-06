package com.puc.tcc.oauth.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OAuthException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;
	
	private String message;
}
