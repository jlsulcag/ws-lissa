package com.sulcacorp.lissa.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulcacorp.lissa.dao.IEspecialidadDAO;
import com.sulcacorp.lissa.model.Especialidad;
import com.sulcacorp.lissa.service.IEspecialidadService;
import com.sulcacorp.lissa.util.Constant;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {
	
	@Autowired
	private IEspecialidadDAO especialidadDAO;

	@Override
	public Especialidad guardar(Especialidad t) {
		t.setDescEspecialidad(StringUtils.upperCase(t.getDescEspecialidad()));
		t.setEstado(Constant.STATUS_ENABLE);
		t.setFechaReg(LocalDateTime.now());
		return especialidadDAO.saveAndFlush(t);
	}

	@Override
	public Especialidad actualizar(Especialidad t) {
		t.setDescEspecialidad(StringUtils.upperCase(t.getDescEspecialidad()));
		t.setFechaReg(t.getFechaReg());
		return especialidadDAO.saveAndFlush(t);
	}

	@Override
	public Especialidad buscar(Long id) {
		Optional<Especialidad> op = especialidadDAO.findById(id);
		return op.isPresent()?op.get(): new Especialidad();
	}

	@Override
	public List<Especialidad> listar() {
		return especialidadDAO.findAll();
	}

	@Override
	public void eliminar(Long id) {
		especialidadDAO.deleteById(id);
	}

}
