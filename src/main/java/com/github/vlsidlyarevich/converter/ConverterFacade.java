package com.github.vlsidlyarevich.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.vlsidlyarevich.converter.factory.ConverterFactory;
import com.github.vlsidlyarevich.dto.ClienteDTO;
import com.github.vlsidlyarevich.dto.UserDTO;
import com.github.vlsidlyarevich.model.Cliente;
import com.github.vlsidlyarevich.model.User;


@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public User convert(final UserDTO dto) {
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
    
    public Cliente convertCliente(final ClienteDTO dto) {
        return (Cliente) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
