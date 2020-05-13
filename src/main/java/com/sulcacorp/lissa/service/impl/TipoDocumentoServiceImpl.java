package com.sulcacorp.lissa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.commons.GenericServiceImpl;
import com.sulcacorp.lissa.dao.ITipoDocumentoDAO;
import com.sulcacorp.lissa.model.TipoDocumento;
import com.sulcacorp.lissa.service.ITipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl extends GenericServiceImpl<TipoDocumento, Long> implements ITipoDocumentoService{

	@Autowired
	private ITipoDocumentoDAO dao;
	
	@Override
	public JpaRepository<TipoDocumento, Long> getDao() {
		return dao;
	}

}
