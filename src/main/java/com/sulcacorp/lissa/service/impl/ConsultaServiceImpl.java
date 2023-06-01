package com.sulcacorp.lissa.service.impl;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.Consulta;
import com.sulcacorp.lissa.repository.IConsultaDAO;
import com.sulcacorp.lissa.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaDAO dao;

	@Override
	public Consulta save(Consulta t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta update(Consulta t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Consulta> findAllAct() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
