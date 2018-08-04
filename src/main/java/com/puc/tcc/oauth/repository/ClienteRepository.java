package com.puc.tcc.oauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.oauth.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
	
	public Optional<Cliente> findById(String id);

}
