package com.github.vlsidlyarevich.repository;

import com.github.vlsidlyarevich.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsername(final String userName);
}
