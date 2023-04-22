package com.sulcacorp.lissa.service;

import java.util.List;

import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IPersonaService extends IGenericService<PersonaDTO, Long>{

	PersonaDTO buscarXDoc(String numDoc) throws Exception;

	List<PersonaDTO> listarFullName(String fullName) throws Exception;

	PersonaDTO buscarXDoc(Long typeDOc, String numDoc) throws Exception;

}
