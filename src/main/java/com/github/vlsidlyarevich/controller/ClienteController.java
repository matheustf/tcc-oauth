package com.github.vlsidlyarevich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.vlsidlyarevich.converter.ConverterFacade;
import com.github.vlsidlyarevich.dto.ClienteDTO;
import com.github.vlsidlyarevich.service.UserService;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final UserService service;

    private final ConverterFacade converterFacade;

    @Autowired
    public ClienteController(final UserService service,
                            final ConverterFacade converterFacade) {
        this.service = service;
        this.converterFacade = converterFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody final ClienteDTO dto) {
        return new ResponseEntity<>(service.addCliente(converterFacade.convertCliente(dto)), HttpStatus.OK);
    }
}
