package com.puc.tcc.oauth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FornecedorDTO {

	private String id;

	@NotNull()
	private String nomeFantasia;
	
	@NotNull()
	private String razaoSocial;
	
	@CNPJ
	@NotNull()
	private String cnpj;
	
	@NotNull()
	private String ramoDeAtividade;
	@URL
	@NotNull
	private String urlDeRetorno;
	
	@Email
	@NotNull
	private String email;
}