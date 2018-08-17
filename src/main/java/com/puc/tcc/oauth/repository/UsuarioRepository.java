package com.puc.tcc.oauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.puc.tcc.oauth.model.Usuario;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Optional<Usuario> findByUsername(final String userName);
}
