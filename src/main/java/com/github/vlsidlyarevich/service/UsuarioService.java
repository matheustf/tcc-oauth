package com.github.vlsidlyarevich.service;

import java.util.List;

import com.github.vlsidlyarevich.dto.DadosClienteDTO;
import com.github.vlsidlyarevich.dto.UsuarioDTO;
import com.github.vlsidlyarevich.model.DadosCliente;
import com.github.vlsidlyarevich.model.Usuario;


public interface UsuarioService {

	Usuario create(UsuarioDTO usuarioDTO);

	Usuario find(String id);

    Usuario findByUsername(String userName);

    List<Usuario> findAll();

    Usuario update(String id, UsuarioDTO usuarioDTO);

    String delete(String id);

    DadosCliente addCliente(DadosClienteDTO dadosClienteDTO);
}
