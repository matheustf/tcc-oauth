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
import com.puc.tcc.oauth.config.email.EmailSenderComponent;
import com.puc.tcc.oauth.consts.Constants;
import com.puc.tcc.oauth.dto.FornecedorDTO;
import com.puc.tcc.oauth.exception.OAuthException;
import com.puc.tcc.oauth.model.Cliente;
import com.puc.tcc.oauth.model.Fornecedor;
import com.puc.tcc.oauth.repository.FornecedorRepository;
import com.puc.tcc.oauth.utils.Util;

@Service
public class FornecedorServiceImpl implements FornecedorService {

	FornecedorRepository fornecedorRepository;
	
	UsuarioService usuarioService;
	
	EmailSenderComponent emailSenderComponent;

	@Autowired
	public FornecedorServiceImpl(FornecedorRepository fornecedorRepository, UsuarioService usuarioService,
			EmailSenderComponent emailSenderComponent) {
		this.fornecedorRepository = fornecedorRepository;
		this.usuarioService = usuarioService;
		this.emailSenderComponent = emailSenderComponent;
	}

	@Override
	public FornecedorDTO consultar(String id) throws OAuthException {

		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		Fornecedor fornecedor = validarFornecedor(optional);

		FornecedorDTO fornecedorDTO = modelMapper().map(fornecedor, FornecedorDTO.class);

		return fornecedorDTO;
	}

	@Override
	public List<FornecedorDTO> buscarTodos() {

		List<Fornecedor> fornecedores = (List<Fornecedor>) fornecedorRepository.findAll();

		Type listType = new TypeToken<List<FornecedorDTO>>() {
		}.getType();
		List<FornecedorDTO> fornecedoresDTO = modelMapper().map(fornecedores, listType);

		return fornecedoresDTO;
	}

	@Override
	public FornecedorDTO incluir(FornecedorDTO fornecedorDTO, String token) throws OAuthException {
		Fornecedor fornecedor = modelMapper().map(fornecedorDTO, Fornecedor.class);

		String idUsuario = Util.getPagameterToken(token, "userID");

		verificarSeUsuarioJaFoiCadastrado(idUsuario);

		fornecedor.setIdUsuario(idUsuario);
		fornecedor.setDataDoCadastro(Util.dataNow());
		fornecedor.setIdFornecedor(Util.gerarCodigo("FORNECEDOR", 5));
		
		usuarioService.updateUsuario(fornecedor.getIdUsuario(), fornecedor.getIdFornecedor(), fornecedor.getEmail());
		
		fornecedorRepository.save(fornecedor);
		
		try {
			emailSenderComponent.emailBoasVindas(fornecedor.getNomeFantasia(), fornecedor.getEmail());
		} catch (Exception e) {
			System.out.println("Error file email");
		}

		return modelMapper().map(fornecedor, FornecedorDTO.class);
	}

	@Override
	public FornecedorDTO atualizar(String id, FornecedorDTO fornecedorDTODetails) throws OAuthException {

		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		Fornecedor fornecedor = validarFornecedor(optional);

		Fornecedor fornecedorDetails = modelMapper().map(fornecedorDTODetails, Fornecedor.class);

		fornecedor = fornecedor.update(fornecedor, fornecedorDetails);

		fornecedorRepository.save(fornecedor);

		FornecedorDTO fornecedorDTO = modelMapper().map(fornecedor, FornecedorDTO.class);

		return fornecedorDTO;
	}

	@Override
	public ResponseEntity<FornecedorDTO> deletar(String id) throws OAuthException {

		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		validarFornecedor(optional);

		fornecedorRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private Fornecedor validarFornecedor(Optional<Fornecedor> optional) throws OAuthException {
		return Optional.ofNullable(optional).get()
				.orElseThrow(() -> new OAuthException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
	
	private void verificarSeUsuarioJaFoiCadastrado(String idUsuario) throws OAuthException {
		Optional<Cliente> optional = fornecedorRepository.findByIdUsuario(idUsuario);

		if (!Optional.empty().equals(optional)) {
			throw new OAuthException(HttpStatus.NOT_FOUND, Constants.USUARIO_EXISTENTE);
		}
		;
	}
}
