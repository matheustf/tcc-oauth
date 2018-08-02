package com.github.vlsidlyarevich.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCliente {

	private String nome;
    private String dataDeNascimento;
    private String cpf;
    private Endereco endereco;
    
	public DadosCliente update(DadosCliente dadosCliente, DadosCliente detailsDadosCliente) {

		dadosCliente.setNome(detailsDadosCliente.getNome());
		dadosCliente.setDataDeNascimento(detailsDadosCliente.getDataDeNascimento());
		dadosCliente.setCpf(detailsDadosCliente.getCpf());
		dadosCliente.setEndereco(dadosCliente.getEndereco());
		
		return dadosCliente;
	}

}
