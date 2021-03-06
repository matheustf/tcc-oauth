package com.puc.tcc.oauth.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.oauth.dto.UsuarioDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.model.Authority;
import com.puc.tcc.oauth.model.Usuario;
import com.puc.tcc.oauth.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<?> getUser(@PathVariable(value = "idUser") String idUser) throws OAuthException {
		return new ResponseEntity<>(service.consultar(idUser), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> getUsers(String idUser) {
		return new ResponseEntity<>(service.buscarTodos(), HttpStatus.OK);
	}

	@PostMapping("/cliente")
	public ResponseEntity<?> criarUsuarioCliente(@RequestBody final UsuarioDTO usuarioDTO) throws OAuthException {
		usuarioDTO.setAuthority(Authority.ROLE_CLIENTE.toString());
		Usuario user = service.incluir(usuarioDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/fornecedor")
	public ResponseEntity<?> criarUsuarioFornecedor(@RequestBody final UsuarioDTO usuarioDTO) throws OAuthException {
		usuarioDTO.setAuthority(Authority.ROLE_FORNECEDOR.toString());
		Usuario user = service.incluir(usuarioDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
