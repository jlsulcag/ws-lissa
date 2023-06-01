package com.sulcacorp.lissa.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulcacorp.lissa.dto.EspecialidadDTO;
import com.sulcacorp.lissa.dto.MedicoDTO;
import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.entity.Especialidad;
import com.sulcacorp.lissa.entity.Medico;
import com.sulcacorp.lissa.entity.Persona;
import com.sulcacorp.lissa.repository.IMedicoDAO;
import com.sulcacorp.lissa.repository.IPersonaDAO;
import com.sulcacorp.lissa.service.IMedicoService;
import com.sulcacorp.lissa.util.Constante;

@Service
public class MedicoServiceImpl implements IMedicoService{

	@Autowired
	private IMedicoDAO medicoDAO;
	
	@Autowired
	private IPersonaDAO personaDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MedicoDTO save(MedicoDTO t) {
		t.setColegiatura(StringUtils.upperCase(t.getColegiatura()));
		t.setEstado(Constante.STATUS_REG_ENABLE);
		t.setFechaReg(LocalDate.now());
		return convertToDTOMedico(medicoDAO.save(convertToEntityMedico(t)));
	}

	@Override
	public MedicoDTO update(MedicoDTO t) {
		t.setColegiatura(StringUtils.upperCase(t.getColegiatura()));
		t.setFechaReg(t.getFechaReg());
		return convertToDTOMedico(medicoDAO.save(convertToEntityMedico(t)));
	}

	@Override
	public MedicoDTO findById(Long id) {
		Optional<Medico> opt = medicoDAO.findById(id);
		return opt.isPresent()?convertToDTOMedico(opt.get()): null;
	}
	
	@Override
	public List<MedicoDTO> findAll() {
		List<Medico> list = new ArrayList<>();
		list = medicoDAO.findAll();
		if(!list.isEmpty()) {
			return list.stream().map(this::convertToDTOMedico).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public List<MedicoDTO> findAllAct() {
		List<Medico> list = new ArrayList<>();
		list = medicoDAO.findAll();
		if(!list.isEmpty()) {
			return list.stream().map(this::convertToDTOMedico).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public void delete(Long id) {
		medicoDAO.deleteById(id);
	}

	@Transactional
	@Override
	public Medico saveCustom(MedicoDTO medicoDTO) throws Exception {
		/*Trasaccion 1 registrar persona*/
		Persona persona = new Persona();
		persona = convertToEntity(medicoDTO.getPersonaDTO());
		persona.setEstado(Constante.STATUS_REG_ENABLE);
		personaDAO.save(persona);
		
		/*Transaccion 2 registrar medico*/
		Especialidad especialidad = new Especialidad();
		especialidad = convertToEntity(medicoDTO.getEspecialidadDTO());
		
		Medico medico = new Medico();
		medico = convertToEntityMedico(medicoDTO);
		medico.setPersona(persona);
		medico.setEspecialidad(especialidad);
		medico.setFechaReg(LocalDate.now());
		medico.setEstado(Constante.STATUS_REG_ENABLE);
		
		medicoDAO.save(medico);
		
		return medico;
	}

	private Persona convertToEntity(PersonaDTO dto) {
		Persona entity = modelMapper.map(dto, Persona.class);		
		return entity;
	}

	private Especialidad convertToEntity(EspecialidadDTO dto) {
		Especialidad entity = modelMapper.map(dto, Especialidad.class);		
		return entity;
	}

	private Medico convertToEntityMedico(MedicoDTO dto) {
		Medico entity = modelMapper.map(dto, Medico.class);		
		return entity;
	}
	
	private MedicoDTO convertToDTOMedico(Medico medico) {
		MedicoDTO dto = modelMapper.map(medico, MedicoDTO.class);		
		return dto;
	}

	@Transactional
	@Override
	public Medico actualizardatos(MedicoDTO medicoDTO) {
		/*Trasaccion 1 actualizar datos de la persona*/
		Persona persona = new Persona();
		persona = convertToEntity(medicoDTO.getPersonaDTO());
		persona.setIdPersona(medicoDTO.getPersonaDTO().getIdPersona());
		personaDAO.save(persona);
		
		/*Transaccion 2 actualizar datos del medico*/
		Especialidad especialidad = new Especialidad();
		especialidad = convertToEntity(medicoDTO.getEspecialidadDTO());
		
		Medico medico = new Medico();
		medico = convertToEntityMedico(medicoDTO);
		medico.setId(medicoDTO.getId());
		//medico.setPersona(persona);
		medico.setEspecialidad(especialidad);
		
		medicoDAO.save(medico);
		
		return medico;
	}

}
