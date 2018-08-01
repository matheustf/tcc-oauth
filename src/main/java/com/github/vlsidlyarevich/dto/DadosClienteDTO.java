package com.github.vlsidlyarevich.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosClienteDTO implements Serializable {

	private static final long serialVersionUID = -1684427972703414690L;

	private String nome;
	private String dataDeNascimento;
	private String cpf;
	private EnderecoDTO endereco;

}
