package com.github.vlsidlyarevich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlsidlyarevich.dto.DadosClienteDTO;
import com.github.vlsidlyarevich.model.DadosCliente;
import com.github.vlsidlyarevich.service.UsuarioService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private final UsuarioService service;

	@Autowired
	public ClienteController(UsuarioService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody DadosClienteDTO dadosClienteDTO) {
		DadosCliente usuario = service.addCliente(dadosClienteDTO);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
}
