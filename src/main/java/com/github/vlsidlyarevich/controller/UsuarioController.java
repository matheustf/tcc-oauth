package com.github.vlsidlyarevich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlsidlyarevich.dto.UsuarioDTO;
import com.github.vlsidlyarevich.model.Usuario;
import com.github.vlsidlyarevich.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<?> getUser(@PathVariable(value = "idUser") String idUser) {
		return new ResponseEntity<>(service.find(idUser), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> getUsers(String idUser) {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> criarUsuario(@RequestBody final UsuarioDTO usuarioDTO) {
		Usuario user = service.create(usuarioDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/{idUser}")
	public ResponseEntity<?> criarCliente(@PathVariable(value = "idUser") String idUser,
			@RequestBody final UsuarioDTO usuarioDTO) {
		Usuario usuario = service.update(idUser, usuarioDTO);

		return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
	}
}
