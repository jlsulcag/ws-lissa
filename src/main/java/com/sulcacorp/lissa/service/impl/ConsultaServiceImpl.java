package com.sulcacorp.lissa.service.impl;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.Consulta;
import com.sulcacorp.lissa.repository.IConsultaDAO;
import com.sulcacorp.lissa.service.IConsultaService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaDAO dao;

	@Override
	public Consulta save(Consulta t) throws CustomServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta update(Consulta t) throws CustomServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta findById(Long id) throws CustomServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> findAllAct() throws CustomServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws CustomServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLogic(Consulta t) throws CustomServiceException {
		// TODO Auto-generated method stub		
	}


}
