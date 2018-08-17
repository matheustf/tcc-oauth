package com.puc.tcc.oauth.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.service.UsuarioService;

@Service
public class BasicUserDetailsService implements UserDetailsService {

	private final UsuarioService userService;

	@Autowired
	public BasicUserDetailsService(final UsuarioService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		try {
			return userService.findByUsername(username);
		} catch (OAuthException e) {
			throw new UsernameNotFoundException("User with username:" + username + " not found");
		}
	}
}
