package com.pcc.tcc.oauth.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pcc.tcc.oauth.dto.ClienteDTO;
import com.pcc.tcc.oauth.exception.OAuthException;

public interface ClienteService {
	
	ClienteDTO consultar(String id) throws OAuthException;
	
	ClienteDTO incluir(ClienteDTO clienteDTO);
	
	ClienteDTO atualizar(String id, ClienteDTO clienteDTODetails) throws OAuthException;
	
	ResponseEntity<ClienteDTO> deletar(String id) throws OAuthException;

	List<ClienteDTO> buscarTodos();

}
