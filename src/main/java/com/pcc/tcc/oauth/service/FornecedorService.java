package com.pcc.tcc.oauth.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pcc.tcc.oauth.dto.FornecedorDTO;
import com.pcc.tcc.oauth.exception.OAuthException;

public interface FornecedorService {
	
	FornecedorDTO consultar(String id) throws OAuthException;
	
	FornecedorDTO incluir(FornecedorDTO fornecedorDTO);
	
	FornecedorDTO atualizar(String id, FornecedorDTO fornecedorDTODetails) throws OAuthException;
	
	ResponseEntity<FornecedorDTO> deletar(String id) throws OAuthException;

	List<FornecedorDTO> buscarTodos();

}
