package com.puc.tcc.oauth.model;

import org.springframework.security.core.GrantedAuthority;


public enum Authority implements GrantedAuthority {
    ROLE_CLIENTE("ROLE_CLIENTE"), 
    ROLE_FORNECEDOR("ROLE_FORNECEDOR"), 
    ROLE_ADMIN("ROLE_ADMIN"); 

    @Override
    public String getAuthority() {
        return this.name();
    }
    
	private String descricao;

	private Authority() {
	}
	
	private Authority(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}
}
