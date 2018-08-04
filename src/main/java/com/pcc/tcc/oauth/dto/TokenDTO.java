package com.pcc.tcc.oauth.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 6710061358371752955L;

    private String token;
    
}
