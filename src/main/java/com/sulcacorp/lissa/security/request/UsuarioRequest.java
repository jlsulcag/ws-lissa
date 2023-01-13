package com.sulcacorp.lissa.security.request;

import java.util.List;

import com.sulcacorp.lissa.model.Persona;
import com.sulcacorp.lissa.model.Rol;

import lombok.Data;

@Data
public class UsuarioRequest {
	
	private String nombreUsuario;
	private String contrasenia;
	private Persona persona;
	private List<Rol> roles;
	
}
