package com.sulcacorp.lissa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.TipoPersona;
import com.sulcacorp.lissa.repository.ITipoPersonaDAO;
import com.sulcacorp.lissa.service.ITipoPersonaService;

@Service
public class TipoPersonaServiceImpl implements ITipoPersonaService{
	
	@Autowired
	private ITipoPersonaDAO dao;


	@Override
	public TipoPersona save(TipoPersona tipoPersona) throws Exception {
		return null;
	}

	@Override
	public TipoPersona update(TipoPersona tipoPersona) throws Exception {
		return null;
	}

	@Override
	public TipoPersona findById(Long aLong) throws Exception {
		return null;
	}

	@Override
	public List<TipoPersona> findAll() throws Exception {
		return null;
	}

	@Override
	public List<TipoPersona> findAllAct() throws Exception {
		return null;
	}

	@Override
	public void delete(Long aLong) throws Exception {

	}

	@Override
	public void deleteLogic(TipoPersona tipoPersona) throws Exception {

	}
}
