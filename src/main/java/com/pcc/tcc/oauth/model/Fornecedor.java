package com.pcc.tcc.oauth.model;

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
public class Fornecedor {

	@Id
	private Long id;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String nomeFantasia;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String razaoSocial;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String cnpj;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String ramoDeAtividade;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String urlDeRetorno;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String email;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String dataDoCadastro;
	
	// TODO private Endereco endereco;
	
	public Fornecedor update(Fornecedor fornecedor, Fornecedor detailsFornecedor) {
		fornecedor.setNomeFantasia(detailsFornecedor.getNomeFantasia());
		fornecedor.setRazaoSocial(detailsFornecedor.getRazaoSocial());
		fornecedor.setCnpj(detailsFornecedor.getCnpj());
		fornecedor.setRamoDeAtividade(detailsFornecedor.getRamoDeAtividade());
		fornecedor.setUrlDeRetorno(detailsFornecedor.getUrlDeRetorno());
		fornecedor.setEmail(detailsFornecedor.getEmail());
		
		return fornecedor;
	}
	
}
