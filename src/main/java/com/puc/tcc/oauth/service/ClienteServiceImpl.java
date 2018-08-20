package com.puc.tcc.oauth.service;

import java.lang.reflect.Type;
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
import com.puc.tcc.oauth.dto.ClienteDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.model.Cliente;
import com.puc.tcc.oauth.repository.ClienteRepository;
import com.puc.tcc.oauth.utils.Util;

@Service
public class ClienteServiceImpl implements ClienteService {

	ClienteRepository clienteRepository;
	
	UsuarioService usuarioService;

	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioService usuarioService) {
		this.clienteRepository = clienteRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	public ClienteDTO consultar(String id) throws OAuthException {
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = validarCliente(optional);
		
		ClienteDTO clienteDTO = modelMapper().map(cliente, ClienteDTO.class);
		
		return clienteDTO;
	}

	@Override
	public List<ClienteDTO> buscarTodos() {

		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

		Type listType = new TypeToken<List<ClienteDTO>>(){}.getType();
		List<ClienteDTO> clientesDTO = modelMapper().map(clientes, listType);

		return clientesDTO;
	}

	@Override
	public ClienteDTO incluir(ClienteDTO clienteDTO) throws OAuthException {
		Cliente cliente = modelMapper().map(clienteDTO, Cliente.class);
		

		cliente.setDataDoCadastro(Util.dataNow());
		cliente.setIdCliente(Util.gerarCodigo("CLIENTE", 5));

		usuarioService.updateIdCadastro(cliente.getIdUsuario(), cliente.getIdCliente());
		
		clienteRepository.save(cliente);
		
		return modelMapper().map(cliente, ClienteDTO.class);
	}

	@Override
	public ClienteDTO atualizar(String id, ClienteDTO clienteDTODetails) throws OAuthException {
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = validarCliente(optional);
		
		Cliente clienteDetails = modelMapper().map(clienteDTODetails, Cliente.class);

		cliente = cliente.update(cliente, clienteDetails);

		clienteRepository.save(cliente);

		ClienteDTO clienteDTO = modelMapper().map(cliente, ClienteDTO.class);

		return clienteDTO;
	}

	@Override
	public ResponseEntity<ClienteDTO> deletar(String id) throws OAuthException {
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		validarCliente(optional);
		
		clienteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Cliente validarCliente(Optional<Cliente> optional) throws OAuthException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new OAuthException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
