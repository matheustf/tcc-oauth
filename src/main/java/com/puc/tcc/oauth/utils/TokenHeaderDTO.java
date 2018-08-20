package com.puc.tcc.oauth.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenHeaderDTO {

	private String id;
	
	private String clientType;
	
	private String userID;

}
