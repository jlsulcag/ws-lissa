package com.sulcacorp.lissa.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtDto {
	
	private String token;
	private String bearer = "Bearer";
	private String usuario;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtDto(String token, String usuario, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.usuario = usuario;
		this.authorities = authorities;
	}
	
	

}
