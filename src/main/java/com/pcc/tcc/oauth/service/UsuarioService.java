package com.pcc.tcc.oauth.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pcc.tcc.oauth.dto.UsuarioDTO;
import com.pcc.tcc.oauth.exception.OAuthException;
import com.pcc.tcc.oauth.model.Usuario;


public interface UsuarioService {

	UsuarioDTO consultar(String id) throws OAuthException;
	
	Usuario incluir(UsuarioDTO usuarioDTO) throws OAuthException;
	
	Usuario atualizar(String id, Usuario usuario) throws OAuthException;
	
	ResponseEntity<UsuarioDTO> deletar(String id) throws OAuthException;

	List<UsuarioDTO> buscarTodos();
	
	Usuario findByUsername(String userName) throws OAuthException;

}
