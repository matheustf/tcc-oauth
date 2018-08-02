package com.github.vlsidlyarevich.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private static final long serialVersionUID = 7954325925563724664L;

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;

	
	public Endereco update(Endereco endereco, Endereco detailsEndereco) {
		endereco.setCep(detailsEndereco.getCep());
		endereco.setLogradouro(detailsEndereco.getLogradouro());
		endereco.setNumero(detailsEndereco.getNumero());
		endereco.setBairro(detailsEndereco.getBairro());
		endereco.setComplemento(detailsEndereco.getComplemento());
		endereco.setCidade(endereco.getCidade());
		endereco.setEstado(detailsEndereco.getEstado());
		
		return endereco;
	}
}
