package com.github.vlsidlyarevich.converter.dto;

import org.springframework.core.convert.converter.Converter;

import com.github.vlsidlyarevich.dto.ClienteDTO;
import com.github.vlsidlyarevich.model.Cliente;


public class ClienteDTOConverter implements Converter<ClienteDTO, Cliente> {

    @Override
    public Cliente convert(final ClienteDTO dto) {
        final Cliente cliente = new Cliente();
        
        
        cliente.setCpf(dto.getCpf());
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setDataDeNascimento(cliente.getDataDeNascimento());
        //cliente.setEndereco(cliente.getEndereco());

        return cliente;
    }
}
