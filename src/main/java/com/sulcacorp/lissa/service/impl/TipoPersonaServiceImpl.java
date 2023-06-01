package com.sulcacorp.lissa.service.impl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.TipoPersona;
import com.sulcacorp.lissa.repository.ITipoPersonaRepository;
import com.sulcacorp.lissa.service.ITipoPersonaService;

@Service
public class TipoPersonaServiceImpl implements ITipoPersonaService{
	
	@Autowired
	private ITipoPersonaRepository repository;


	@Override
	public TipoPersona save(TipoPersona tipoPersona) throws Exception {
		tipoPersona.setDescripcion(StringUtils.upperCase(tipoPersona.getDescripcion()));
		return repository.save(tipoPersona);
	}

	@Override
	public TipoPersona update(TipoPersona tipoPersona) throws Exception {
		tipoPersona.setDescripcion(StringUtils.upperCase(tipoPersona.getDescripcion()));
		return repository.save(tipoPersona);
	}

	@Override
	public TipoPersona findById(Long id) throws Exception {
		Optional<TipoPersona> op = repository.findById(id);
		return op.isPresent()?op.get():null;
	}

	@Override
	public List<TipoPersona> findAll() throws Exception {
		List<TipoPersona> list = repository.findAll(Sort.by(Sort.Direction.ASC, "descripcion"));
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		return list;
	}

	@Override
	public List<TipoPersona> findAllAct() throws Exception {
		return Collections.emptyList();
	}

	@Override
	public void delete(Long aLong) throws Exception {

	}
}
