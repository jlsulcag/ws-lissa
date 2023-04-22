package com.sulcacorp.lissa.security.service;

import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.security.request.UsuarioRequest;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IUsuarioService extends IGenericService<Usuario, Long>{

	Integer createUser(UsuarioRequest usuarioRequest) throws Exception;
	
	boolean existsByNombreUsuario(String nombreUsuario) throws Exception;
	
}
