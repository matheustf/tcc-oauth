package com.puc.tcc.oauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.oauth.model.Cliente;
import com.puc.tcc.oauth.model.Fornecedor;

@Repository
public interface FornecedorRepository extends MongoRepository<Fornecedor, String> {
	
	public Optional<Fornecedor> findById(String id);
	
	public Optional<Cliente> findByIdUsuario(String idUsuario);

}
