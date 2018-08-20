package com.puc.tcc.oauth.service;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.puc.tcc.oauth.consts.Constants;
import com.puc.tcc.oauth.dto.UsuarioDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.model.Authority;
import com.puc.tcc.oauth.model.Usuario;
import com.puc.tcc.oauth.repository.UsuarioRepository;
import com.puc.tcc.oauth.utils.Util;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UsuarioDTO consultar(String id) throws OAuthException {
		
		Optional<Usuario> optional = usuarioRepository.findById(id);
		Usuario usuario = validarUsuario(optional);
		
		UsuarioDTO usuarioDTO = modelMapper().map(usuario, UsuarioDTO.class);
		
		return usuarioDTO;
	}

	@Override
	public List<UsuarioDTO> buscarTodos() {

		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

		Type listType = new TypeToken<List<UsuarioDTO>>(){}.getType();
		List<UsuarioDTO> usuariosDTO = modelMapper().map(usuarios, listType);

		return usuariosDTO;
	}

	@Override
	public Usuario incluir(UsuarioDTO usuarioDTO) throws OAuthException {
		Usuario usuario = modelMapper().map(usuarioDTO, Usuario.class);
		
		usuario.setPassword(Util.criptografarMD5(usuario.getPassword()));
		
		validarLoginDeUsuarioNovo(usuario);
		
		Authority authority = Authority.valueOf(usuarioDTO.getAuthority());
		
		usuario.setAuthorities(Arrays.asList(authority));
		
		usuarioRepository.save(usuario);
		
		return usuario;
	}

	private void validarLoginDeUsuarioNovo(Usuario usuario) throws OAuthException {
		Optional<Usuario> optional = usuarioRepository.findByUsername(usuario.getUsername());
		
		if (optional.isPresent()) {
			throw new OAuthException(HttpStatus.CONFLICT, Constants.USUARIO_EXISTENTE);
		}
	}

	@Override
	public Usuario atualizar(String id, Usuario usuarioDetails) throws OAuthException {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		Usuario usuario = validarUsuario(optional);
		
		usuario = usuario.update(usuario, usuarioDetails);

		usuarioRepository.save(usuario);

		return usuario;
	}

	@Override
	public ResponseEntity<UsuarioDTO> deletar(String id) throws OAuthException {
		
		Optional<Usuario> optional = usuarioRepository.findById(id);
		validarUsuario(optional);
		
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Usuario validarUsuario(Optional<Usuario> optional) throws OAuthException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new OAuthException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}

	@Override
	public Usuario findByUsername(String userName) throws OAuthException {
		Optional<Usuario> optional = usuarioRepository.findByUsername(userName);
		Usuario usuario = validarUsuario(optional);
		
		//TODO DTO UsuarioDTO usuarioDTO = modelMapper().map(usuario, UsuarioDTO.class);
		
		return usuario;
	}

	@Override
	public void updateIdCadastro(String idUsuario, String idCadastro) throws OAuthException {
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
		Usuario usuario = validarUsuario(optional);
	
		usuario.setIdCadastro(idCadastro);

		usuarioRepository.save(usuario);
	}
}
