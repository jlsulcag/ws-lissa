package com.sulcacorp.lissa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.TipoMedico;
import com.sulcacorp.lissa.repository.ITipoMedicoDAO;
import com.sulcacorp.lissa.service.ITipoMedicoService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

@Service
public class TipoMedicoServiceImpl implements ITipoMedicoService {

	@Autowired
	private ITipoMedicoDAO tipoMedicoDAO;
	
	@Override
	public TipoMedico save(TipoMedico t) {
		t.setDescTipoMedico(t.getDescTipoMedico().toUpperCase());
		t.setEstado(Constant.STATUS_REG_ENABLE);
		return tipoMedicoDAO.save(t);
	}

	@Override
	public TipoMedico update(TipoMedico t) {
		t.setDescTipoMedico(t.getDescTipoMedico().toUpperCase());
		return tipoMedicoDAO.save(t);
	}

	@Override
	public TipoMedico findById(Long id) {
		Optional<TipoMedico> op = tipoMedicoDAO.findById(id);
		return op.isPresent()?op.get(): null;
	}

	@Override
	public List<TipoMedico> findAll() {
		return tipoMedicoDAO.findAllAct();
	}
	
	@Override
	public List<TipoMedico> findAllAct() {
		return tipoMedicoDAO.findAllAct();
	}

	@Override
	public void delete(Long id) {
		tipoMedicoDAO.deleteById(id);		
	}

	@Override
	public void deleteLogic(TipoMedico t) throws CustomServiceException {
		
	}

}
