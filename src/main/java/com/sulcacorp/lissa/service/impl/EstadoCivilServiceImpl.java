package com.sulcacorp.lissa.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.dto.EstadoCivilDTO;
import com.sulcacorp.lissa.entity.EstadoCivil;
import com.sulcacorp.lissa.repository.IEstadoCivilDAO;
import com.sulcacorp.lissa.service.IEstadoCivilService;

@Service
public class EstadoCivilServiceImpl implements IEstadoCivilService{

	@Autowired
	private IEstadoCivilDAO dao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EstadoCivilDTO save(EstadoCivilDTO t) throws Exception {
		t.setDescripcion(t.getDescripcion().toUpperCase().trim());		
		return convertToDto(dao.saveAndFlush(convertToEntity(t)));
	}

	@Override
	public EstadoCivilDTO update(EstadoCivilDTO t) throws Exception {
		t.setDescripcion(t.getDescripcion().toUpperCase().trim());		
		return convertToDto(dao.save(convertToEntity(t)));
	}	

	@Override
	public EstadoCivilDTO findById(Long id) throws Exception {
		Optional<EstadoCivil> opt = dao.findById(id);
		return opt.isPresent()?convertToDto(opt.get()):null;
	}
	
	@Override
	public List<EstadoCivilDTO> findAll() throws Exception {
		List<EstadoCivil> list = null;
		list = dao.findAllAct();
		if(!list.isEmpty()) {
			return list.stream().map(this::convertToDto).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public List<EstadoCivilDTO> findAllAct() throws Exception {
		List<EstadoCivil> list = null;
		list = dao.findAllAct();
		if(!list.isEmpty()) {
			return list.stream().map(this::convertToDto).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.deleteById(id);		
	}
	
	private EstadoCivilDTO convertToDto(EstadoCivil entity) {
		EstadoCivilDTO estadoCivilDTO = modelMapper.map(entity, EstadoCivilDTO.class);
		return estadoCivilDTO;
	}

	private EstadoCivil convertToEntity(EstadoCivilDTO dto) {
		EstadoCivil estadoCivil = modelMapper.map(dto, EstadoCivil.class);		
		return estadoCivil;
	}

	@Override
	public void deleteLogic(EstadoCivilDTO t) throws Exception {
		dao.deleteLogic(t.getIdEstadoCivil());
	}	

}
