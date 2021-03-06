package com.puc.tcc.oauth.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.puc.tcc.oauth.dto.ClienteDTO;
import com.puc.tcc.oauth.dto.EnderecoDTO;
import com.puc.tcc.oauth.exception.OAuthException;

public interface ClienteService {
	
	ClienteDTO consultar(String id) throws OAuthException;
	
	ClienteDTO incluir(ClienteDTO clienteDTO, String token) throws OAuthException;
	
	ClienteDTO atualizar(String id, ClienteDTO clienteDTODetails) throws OAuthException;
	
	ResponseEntity<ClienteDTO> deletar(String id) throws OAuthException;

	List<ClienteDTO> buscarTodos();

	void inserirEndereco(@Valid EnderecoDTO enderecoDTO, String token) throws OAuthException;

	EnderecoDTO buscarEndereco(String token) throws OAuthException;

}
