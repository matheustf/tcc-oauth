package com.github.vlsidlyarevich.security.service;

import com.github.vlsidlyarevich.model.Usuario;
import com.github.vlsidlyarevich.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BasicUserDetailsService implements UserDetailsService {

    private final UsuarioService userService;

    @Autowired
    public BasicUserDetailsService(final UsuarioService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Usuario user = userService.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User with username:" + username + " not found");
        }
    }
}
