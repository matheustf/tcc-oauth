package com.puc.tcc.oauth.config.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

	private String nomeUsuario;
    private String remetente;
    private String destinatario;
    private String titulo;
    
}