package com.puc.tcc.oauth.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.puc.tcc.oauth.dto.FornecedorDTO;
import com.puc.tcc.oauth.exception.OAuthException;

public interface FornecedorService {
	
	FornecedorDTO consultar(String id) throws OAuthException;
	
	FornecedorDTO incluir(FornecedorDTO fornecedorDTO, String token) throws OAuthException;
	
	FornecedorDTO atualizar(String id, FornecedorDTO fornecedorDTODetails) throws OAuthException;
	
	ResponseEntity<FornecedorDTO> deletar(String id) throws OAuthException;

	List<FornecedorDTO> buscarTodos();

}
