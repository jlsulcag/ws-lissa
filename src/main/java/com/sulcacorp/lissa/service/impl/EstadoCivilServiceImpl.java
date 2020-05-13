package com.sulcacorp.lissa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.commons.GenericServiceImpl;
import com.sulcacorp.lissa.dao.IEstadoCivilDAO;
import com.sulcacorp.lissa.model.EstadoCivil;
import com.sulcacorp.lissa.service.IEstadoCivilService;

@Service
public class EstadoCivilServiceImpl extends GenericServiceImpl<EstadoCivil, Long> implements IEstadoCivilService{

	@Autowired
	private IEstadoCivilDAO dao;
	
	@Override
	public JpaRepository<EstadoCivil, Long> getDao() {
		return dao;
	}

}
