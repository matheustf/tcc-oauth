package com.puc.tcc.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	private String pais;
	private String destinatario;
}
