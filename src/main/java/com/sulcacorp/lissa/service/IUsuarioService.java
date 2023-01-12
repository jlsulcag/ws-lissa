package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.request.UsuarioRequest;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IUsuarioService extends IGenericService<Usuario, Long>{

	Integer createUser(UsuarioRequest usuarioRequest) throws CustomServiceException;
	
}
