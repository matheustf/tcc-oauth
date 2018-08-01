package com.github.vlsidlyarevich.dto;

import java.io.Serializable;

import com.github.vlsidlyarevich.model.User;


public class ClienteDTO implements Serializable {

    private String nome;
    private String sobrenome;
    private String dataDeNascimento;
    private String cpf;
    private EnderecoDTO endereco;
    private User user;
    
    public ClienteDTO() {
    	
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
    
    

    
}
