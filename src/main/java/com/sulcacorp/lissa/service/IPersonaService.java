package com.sulcacorp.lissa.service;

import java.util.List;

import com.sulcacorp.lissa.commons.IGenericService;
import com.sulcacorp.lissa.model.Persona;

public interface IPersonaService extends IGenericService<Persona, Long>{

	Persona buscarXDoc(String numDoc);

	List<Persona> listarFullName(String fullName);

	Persona buscarXDoc(Long typeDOc, String numDoc);

}
