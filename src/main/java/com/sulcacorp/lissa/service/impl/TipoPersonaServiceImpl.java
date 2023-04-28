package com.sulcacorp.lissa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.TipoPersona;
import com.sulcacorp.lissa.repository.ITipoPersonaDAO;
import com.sulcacorp.lissa.service.ITipoPersonaService;
import com.sulcacorp.lissa.service.generic.GenericServiceImpl;

@Service
public class TipoPersonaServiceImpl extends GenericServiceImpl<TipoPersona, Long> implements ITipoPersonaService{
	
	@Autowired
	private ITipoPersonaDAO dao;

	@Override
	public JpaRepository<TipoPersona, Long> getDao() {
		return dao;
	}

	@Override
	public void deleteLogic(TipoPersona t) throws Exception {
		
	}

	@Override
	public List<TipoPersona> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
