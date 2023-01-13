package com.sulcacorp.lissa.security.request;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String usuario;
	private String contrasenia;

}
