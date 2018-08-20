package com.puc.tcc.oauth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -1684427972703414690L;

	private String id;
	
	private String idCliente;
	
	@NotNull()
	private String idUsuario;
	
	@NotNull()
	private String nome;
	
	@NotNull()
	private String dataDeNascimento;
	
	@CPF
	@NotNull()
	private String cpf;
	
	private EnderecoDTO endereco;

}
