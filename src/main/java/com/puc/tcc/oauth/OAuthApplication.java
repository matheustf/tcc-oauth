package com.puc.tcc.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.puc.tcc.oauth.model.Authority;
import com.puc.tcc.oauth.model.Usuario;
import com.puc.tcc.oauth.repository.UsuarioRepository;


@SpringBootApplication
public class OAuthApplication implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    public static void main(final String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
    
    @Override
	public void run(String... arg0) throws Exception {
		//clearRepositories.clear();
		
    	Usuario admin = Usuario.builder()
    			.id("5b65dbf8397d8817426f7130")
    			.username("admin")
    			.password("5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5")
    			.accountNonExpired(true)
    			.accountNonLocked(true)
    			.credentialsNonExpired(true)
    			.isEnabled(true)
    			.authorities(Arrays.asList(Authority.ROLE_ADMIN))
    			.build();
    	
    	usuarioRepository.save(admin);
    	
	}


}
