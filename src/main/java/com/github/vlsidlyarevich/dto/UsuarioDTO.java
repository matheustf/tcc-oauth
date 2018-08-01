package com.github.vlsidlyarevich.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 91901774547107674L;

    private String id;
    private String username;
    private String password;
    private DadosClienteDTO dados;
    
}
