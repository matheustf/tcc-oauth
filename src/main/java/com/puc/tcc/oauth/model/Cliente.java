package com.puc.tcc.oauth.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Cliente implements Serializable {

	@Id
    private String id;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String idUsuario;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String idCliente;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String nome;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String dataDeNascimento;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String cpf;

	@NotNull(message = "Campo Obrigatorio!")
	private String dataDoCadastro;
	
	private Endereco endereco;
	
	
	public Cliente update(Cliente cliente, Cliente detailsCliente) {

		cliente.setNome(detailsCliente.getNome());
		cliente.setDataDeNascimento(detailsCliente.getDataDeNascimento());
		cliente.setCpf(detailsCliente.getCpf());
		cliente.setEndereco(detailsCliente.getEndereco());
		
		return cliente;
	}
}
