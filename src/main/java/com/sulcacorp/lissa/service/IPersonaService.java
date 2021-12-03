package com.sulcacorp.lissa.service;

import java.util.List;

import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IPersonaService extends IGenericService<PersonaDTO, Long>{

	PersonaDTO buscarXDoc(String numDoc) throws CustomServiceException;

	List<PersonaDTO> listarFullName(String fullName) throws CustomServiceException;

	PersonaDTO buscarXDoc(Long typeDOc, String numDoc) throws CustomServiceException;

}
