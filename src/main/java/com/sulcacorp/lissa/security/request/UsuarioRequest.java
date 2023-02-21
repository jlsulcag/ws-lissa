package com.sulcacorp.lissa.security.request;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import com.sulcacorp.lissa.model.Persona;
import com.sulcacorp.lissa.model.Rol;

import lombok.Data;

@Data
public class UsuarioRequest {
	
	@NotBlank
	private String nombreUsuario;
	
	@NotBlank
	private String contrasenia;
	
	
	private Persona persona;
	private Set<Rol> roles = new HashSet<>();
	
}