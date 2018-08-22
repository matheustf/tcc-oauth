package com.puc.tcc.oauth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.oauth.dto.ClienteDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.service.ClienteService;
import com.puc.tcc.oauth.validate.TokenValidate;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private final ClienteService clienteService;
	private TokenValidate tokenValidate;

	@Autowired
	public ClienteController(ClienteService clienteService, TokenValidate tokenValidate) {
		this.clienteService = clienteService;
		this.tokenValidate = tokenValidate;
	}
	
	@GetMapping()
	@RequestMapping("")
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {

		List<ClienteDTO> listCompras = clienteService.buscarTodos();

		return new ResponseEntity<List<ClienteDTO>>(listCompras, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> consultar(@PathVariable(value = "id") String id, @PathVariable(value = "id") String idCompra, @RequestHeader(value = "x-access-token") String token) throws OAuthException{
		tokenValidate.tokenValidate(token);
		
		ClienteDTO compraDTO = clienteService.consultar(idCompra);

		return new ResponseEntity<ClienteDTO>(compraDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ClienteDTO> incluir(@RequestBody @Valid ClienteDTO compraDTO, @RequestHeader(value = "x-access-token") String token) throws OAuthException {
		tokenValidate.tokenSimpleValidate(token);
		
		ClienteDTO responseCompraDTO = clienteService.incluir(compraDTO, token);
		return new ResponseEntity<ClienteDTO>(responseCompraDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid ClienteDTO compraDTODetails) throws OAuthException {

		ClienteDTO compraDTO = clienteService.atualizar(id, compraDTODetails);

		return new ResponseEntity<ClienteDTO>(compraDTO, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDTO> deletar(@PathVariable(value = "id") String id, @RequestHeader(value = "x-access-token") String token) throws OAuthException {

		ResponseEntity<ClienteDTO> response = clienteService.deletar(id);
		
		return response;
	}
}
