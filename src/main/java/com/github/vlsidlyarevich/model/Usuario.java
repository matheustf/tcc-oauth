package com.github.vlsidlyarevich.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Usuario extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 7954325925563724664L;

    private String id;
    private List<Authority> authorities;
    private String username;
    private String password;
    private DadosCliente dados;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    
	public Usuario update(Usuario usuario, Usuario detailsUsuario) {
		usuario.setUsername(detailsUsuario.getUsername());
		usuario.setPassword(detailsUsuario.getPassword());
		usuario.setDados(detailsUsuario.getDados());
		//usuario.setAuthorities(detailsUsuario);
		usuario.setAccountNonExpired(detailsUsuario.isAccountNonExpired());
		usuario.setAccountNonLocked(detailsUsuario.isAccountNonLocked());
		usuario.setCredentialsNonExpired(detailsUsuario.isCredentialsNonExpired());
		
		return usuario;
	}

}
