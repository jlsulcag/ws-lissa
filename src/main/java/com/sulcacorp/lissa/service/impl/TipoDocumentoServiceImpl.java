package com.sulcacorp.lissa.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.TipoDocumento;
import com.sulcacorp.lissa.repository.ITipoDocumentoDAO;
import com.sulcacorp.lissa.service.ITipoDocumentoService;
import com.sulcacorp.lissa.util.Constante;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoDAO dao;

	@Override
	public TipoDocumento save(TipoDocumento t) throws Exception {
		t.setDescripcion(StringUtils.isBlank(t.getDescripcion()) ? "" : t.getDescripcion().toUpperCase().trim());
		t.setAbreviatura(StringUtils.isBlank(t.getAbreviatura()) ? null : t.getAbreviatura().toUpperCase().trim());
		t.setCodigo(StringUtils.isBlank(t.getCodigo()) ? null : t.getCodigo().toUpperCase().trim());
		t.setEstado(Constante.STATUS_REG_ENABLE);
		return dao.save(t);

	}

	@Override
	public TipoDocumento update(TipoDocumento t) throws Exception {
		t.setDescripcion(StringUtils.isBlank(t.getDescripcion()) ? "" : t.getDescripcion().toUpperCase().trim());
		t.setAbreviatura(StringUtils.isBlank(t.getAbreviatura()) ? null : t.getAbreviatura().toUpperCase().trim());
		t.setCodigo(StringUtils.isBlank(t.getCodigo()) ? null : t.getCodigo().toUpperCase().trim());
		return dao.save(t);
	}

	@Override
	public TipoDocumento findById(Long id) throws Exception {
		Optional<TipoDocumento> opt = dao.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public List<TipoDocumento> findAll() throws Exception {
		return dao.findAll();
	}
	
	@Override
	public List<TipoDocumento> findAllAct() throws Exception {
		return dao.findAllAct();
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.deleteById(id);
	}

}
