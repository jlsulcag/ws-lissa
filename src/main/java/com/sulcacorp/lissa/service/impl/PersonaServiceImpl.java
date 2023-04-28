package com.sulcacorp.lissa.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.entity.Persona;
import com.sulcacorp.lissa.repository.IPersonaDAO;
import com.sulcacorp.lissa.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDAO dao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PersonaDTO buscarXDoc(String numDoc) throws Exception {
		return convertToDto(dao.buscarXDni(numDoc));
	}

	@Override
	public List<PersonaDTO> listarFullName(String fullName) throws Exception {
		List<Persona> listPerson = dao.listarFullName(fullName);
		if (!listPerson.isEmpty()) {
			return listPerson.stream().map(this::convertToDto).collect(Collectors.toList());
		}		
		return new ArrayList<>();
	}

	@Override
	public PersonaDTO buscarXDoc(Long typeDOc, String docNumber) throws Exception {
		Persona persona = new Persona();
		persona = dao.buscarXDoc(typeDOc, docNumber);
		if(persona != null && persona.getIdPersona() > 0L) {
			return convertToDto(dao.buscarXDoc(typeDOc, docNumber));
		}
		return null;
		
	}

	@Override
	public PersonaDTO save(PersonaDTO t) throws Exception {
		Persona persona = new Persona();
		persona = convertToEntity(t);
		return convertToDto(dao.save(persona));
	}

	@Override
	public PersonaDTO update(PersonaDTO t) throws Exception {
		Persona persona = new Persona();
		persona = convertToEntity(t);
		return convertToDto(dao.save(persona));
	}

	@Override
	public PersonaDTO findById(Long id) throws Exception {
		Optional<Persona> opt = dao.findById(id);
		return opt.isPresent()? convertToDto(opt.get()) : null;
	}
	
	@Override
	public List<PersonaDTO> findAll() throws Exception {
		List<Persona> list = new ArrayList<>();
		List<PersonaDTO> listDto = new ArrayList<>();
		list = dao.findAll();
		if(!list.isEmpty()) {
			for (Persona persona : list) {
				listDto.add(convertToDto(persona));
			}
			return listDto;
		}
		return listDto;
	}

	@Override
	public List<PersonaDTO> findAllAct() throws Exception {
		List<Persona> list = new ArrayList<>();
		List<PersonaDTO> listDto = new ArrayList<>();
		list = dao.findAllAct();
		if(!list.isEmpty()) {
			for (Persona persona : list) {
				listDto.add(convertToDto(persona));
			}
			return listDto;
		}
		return listDto;
	}

	@Override
	public void delete(Long id) throws Exception {
		dao.deleteById(id);		
	}
	
	
	private PersonaDTO convertToDto(Persona entity) {
		PersonaDTO dto = modelMapper.map(entity, PersonaDTO.class);
		return dto;
	}

	private Persona convertToEntity(PersonaDTO dto) {
		Persona entity = modelMapper.map(dto, Persona.class);		
		return entity;
	}

	@Override
	public void deleteLogic(PersonaDTO t) throws Exception {
		
	}

}
