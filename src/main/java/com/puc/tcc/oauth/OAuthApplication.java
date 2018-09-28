package com.puc.tcc.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.puc.tcc.oauth.model.Authority;
import com.puc.tcc.oauth.model.Fornecedor;
import com.puc.tcc.oauth.model.Usuario;
import com.puc.tcc.oauth.repository.FornecedorRepository;
import com.puc.tcc.oauth.repository.UsuarioRepository;
import com.puc.tcc.oauth.utils.Util;

@EnableDiscoveryClient
@SpringBootApplication
public class OAuthApplication implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

    public static void main(final String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
    
    @Override
	public void run(String... arg0) throws Exception {
		//clearRepositories.clear();
    	
    	Usuario admin = Usuario.builder()
    			.id("5b65dbf8397d8817426f7130")
    			.username("admin")
    			.password("2276DBDC8CF4CAEA4A3CA942F8F2BF900760A70FF306BD4D67B9B36339A982A2")
    			.accountNonExpired(true)
    			.accountNonLocked(true)
    			.credentialsNonExpired(true)
    			.isEnabled(true)
    			.authorities(Arrays.asList(Authority.ROLE_ADMIN))
    			.build();
    	
    	Fornecedor fornecedorAdidas = Fornecedor.builder()
    			.id("4324j32jk432jk432")
    			.cnpj("16160328000105")
    			.dataDoCadastro(Util.dataNow())
    			.nomeFantasia("Adidas")
    			.razaoSocial("Adidas LTDA")
    			.email("teles.matheus@hotmail.com")
    			.ramoDeAtividade("calçados")
    			.idUsuario("5b65dbf8397d8817426f7130")
    			.idFornecedor("FORNECEDOR-DQSDW")
    			.build();

    	Fornecedor fornecedorNike = Fornecedor.builder()
    			.id("askd32oo324o32nfds")
    			.cnpj("16160328000105")
    			.dataDoCadastro(Util.dataNow())
    			.nomeFantasia("Nike")
    			.razaoSocial("Nike LTDA")
    			.email("teles.matheus@hotmail.com")
    			.ramoDeAtividade("calçados")
    			.idUsuario("5b65dbf8397d8817426f7130")
    			.idFornecedor("FORNECEDOR-FEFTR")
    			.build();
    	
    	usuarioRepository.save(admin);
    	
    	fornecedorRepository.insert(Arrays.asList(fornecedorAdidas,fornecedorNike));
	}


}
