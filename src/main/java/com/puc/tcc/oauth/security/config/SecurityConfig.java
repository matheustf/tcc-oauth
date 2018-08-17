package com.puc.tcc.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.puc.tcc.oauth.security.filter.AuthenticationTokenFilter;
import com.puc.tcc.oauth.security.service.TokenAuthenticationService;


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
                 .anyRequest().authenticated()
                 .and()
                 .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService),
                         UsernamePasswordAuthenticationFilter.class)
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .csrf().disable();
    }
    
}