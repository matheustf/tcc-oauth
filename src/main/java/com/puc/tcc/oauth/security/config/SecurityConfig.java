package com.puc.tcc.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.oauth.model.Authority;
import com.puc.tcc.oauth.security.filter.AuthenticationTokenFilter;
import com.puc.tcc.oauth.security.service.TokenAuthenticationService;

@RestController
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    protected SecurityConfig(final TokenAuthenticationService tokenAuthenticationService) {
        super();
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/").permitAll()
         .antMatchers("/api/auth").permitAll()
         .antMatchers("/api/hello").permitAll()
         .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                 .antMatchers("/api/usuario/cliente").permitAll()
                 .antMatchers("/api/cliente").hasAuthority("ROLE_CLIENTE")
                 .antMatchers("/api/fornecedor").hasAuthority("ROLE_FORNECEDOR")
                 //Avaliacao
                 .antMatchers(HttpMethod.GET,"/entrega/avaliacoes/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.POST,"/entrega/avaliacoes/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.PUT,"/entrega/avaliacoes/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.GET,"/entrega/avaliacoes/{[A-z0-9-]+}").hasAnyAuthority("ROLE_ADMIN")
                 .antMatchers(HttpMethod.DELETE,"/entrega/avaliacoes/{[A-z0-9-]+}").hasAnyAuthority("ROLE_ADMIN")
                 //Entrega
                 .antMatchers(HttpMethod.GET,"/entrega/entregas/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.POST,"/entrega/entregas/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.PUT,"/entrega/entregas/**").hasAnyAuthority("ROLE_CLIENTE")
                 .antMatchers(HttpMethod.GET,"/entrega/entregas/{[A-z0-9-]+}").hasAnyAuthority("ROLE_ADMIN")
                 .antMatchers(HttpMethod.DELETE,"/entrega/entregas/{[A-z0-9-]+}").hasAnyAuthority("ROLE_ADMIN")
                 
                 //Produtos
                 .antMatchers("/vendas/**").permitAll()
                 .antMatchers(HttpMethod.POST,"/vendas/produtos/{[A-z0-9-]+}/aprovar").hasAnyAuthority("ROLE_ADMIN")
                 .antMatchers(HttpMethod.POST,"/vendas/produtos/{[A-z0-9-]+}/disponibilizar").hasAnyAuthority("ROLE_FORNECEDOR")
                 .antMatchers(HttpMethod.POST,"/vendas/produtos/{[A-z0-9-]+}/indisponibilizar").hasAnyAuthority("ROLE_FORNECEDOR")
                 .antMatchers(HttpMethod.POST,"/vendas/produtos}").hasAnyAuthority("ROLE_FORNECEDOR")
                 .antMatchers(HttpMethod.GET,"/vendas/produtos/{[A-z0-9-]+}").hasAnyAuthority("ROLE_FORNECEDOR")
                 .antMatchers(HttpMethod.PUT,"/vendas/produtos/{[A-z0-9-]+}").hasAnyAuthority("ROLE_FORNECEDOR")
                 .antMatchers(HttpMethod.DELETE,"/vendas/produtos/{[A-z0-9-]+}").hasAnyAuthority("ROLE_FORNECEDOR")
                 
                 .anyRequest().authenticated()
                 .and()
                 .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService),
                         UsernamePasswordAuthenticationFilter.class)
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .csrf().disable();
    }
    
	@RequestMapping({
		"/entrega/avaliacoes/cliente", 
		"/entrega/avaliacoes",
		"/vendas/produtos",
		"/vendas/produtos/{[A-z0-9-]+}/aprovar",
		"/vendas/produtos/{[A-z0-9-]+}/disponibilizar",
		"/vendas/produtos/{[A-z0-9-]+}/indisponibilizar",
		"/vendas/produtos/{[A-z0-9-]+}"
		})
	public ResponseEntity<String> mappingAPI() {
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
    
}