package com.github.vlsidlyarevich.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.github.vlsidlyarevich.dto.DadosClienteDTO;
import com.github.vlsidlyarevich.dto.UsuarioDTO;
import com.github.vlsidlyarevich.model.DadosCliente;
import com.github.vlsidlyarevich.model.Usuario;
import com.github.vlsidlyarevich.repository.UserRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UserRepository repository;

	@Autowired
	public UsuarioServiceImpl(final UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Usuario create(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper().map(usuarioDTO, Usuario.class);

		usuario.setCreatedAt(String.valueOf(LocalDateTime.now()));
		return repository.save(usuario);
	}

	@Override
	public Usuario find(String id) {
		return repository.findOne(id);
	}

	@Override
	public Usuario findByUsername(String nome) {
		return repository.findByUsername(nome);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public Usuario update(String id, UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper().map(usuarioDTO, Usuario.class);
		usuario.setId(id);

		Usuario saved = repository.findOne(id);

		if (saved != null) {
			usuario.setCreatedAt(saved.getCreatedAt());
			usuario.setUpdatedAt(String.valueOf(LocalDateTime.now()));
		} else {
			usuario.setCreatedAt(String.valueOf(LocalDateTime.now()));
		}
		repository.save(usuario);
		return usuario;
	}

	@Override
	public String delete(String id) {
		repository.delete(id);
		return id;
	}

	@Override
	public DadosCliente addCliente(DadosClienteDTO dadosClienteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
