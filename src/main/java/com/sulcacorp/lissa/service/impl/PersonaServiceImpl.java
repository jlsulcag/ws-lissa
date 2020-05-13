package com.sulcacorp.lissa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.commons.GenericServiceImpl;
import com.sulcacorp.lissa.dao.IPersonaDAO;
import com.sulcacorp.lissa.model.Persona;
import com.sulcacorp.lissa.service.IPersonaService;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Long> implements IPersonaService {

	@Autowired
	private IPersonaDAO dao;

	@Override
	public JpaRepository<Persona, Long> getDao() {
		return dao;
	}

	@Override
	public Persona buscarXDoc(String numDoc) {
		return dao.buscarXDni(numDoc);
	}

	@Override
	public List<Persona> listarFullName(String fullName) {
		return dao.listarFullName(fullName);
	}

	@Override
	public Persona buscarXDoc(Long typeDOc, String numDoc) {
		return dao.buscarXDoc(typeDOc, numDoc);
	}
	
	
	

}
