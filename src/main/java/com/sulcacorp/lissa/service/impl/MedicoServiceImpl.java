package com.sulcacorp.lissa.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulcacorp.lissa.dto.EspecialidadDTO;
import com.sulcacorp.lissa.dto.MedicoDTO;
import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.model.Especialidad;
import com.sulcacorp.lissa.model.Medico;
import com.sulcacorp.lissa.model.Persona;
import com.sulcacorp.lissa.repository.IMedicoDAO;
import com.sulcacorp.lissa.repository.IPersonaDAO;
import com.sulcacorp.lissa.service.IMedicoService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

@Service
public class MedicoServiceImpl implements IMedicoService{

	@Autowired
	private IMedicoDAO medicoDAO;
	
	@Autowired
	private IPersonaDAO personaDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Medico save(Medico t) {
		t.setColegiatura(StringUtils.upperCase(t.getColegiatura()));
		t.setEstado(Constant.STATUS_ENABLE);
		t.setFechaReg(LocalDate.now());
		return medicoDAO.save(t);
	}

	@Override
	public Medico update(Medico t) {
		t.setColegiatura(StringUtils.upperCase(t.getColegiatura()));
		t.setFechaReg(t.getFechaReg());
		return medicoDAO.save(t);
	}

	@Override
	public Medico findById(Long id) {
		Optional<Medico> opt = medicoDAO.findById(id);
		return opt.isPresent()?opt.get(): null;
	}

	@Override
	public List<Medico> findAllAct() {
		
		return medicoDAO.findAll();
	}

	@Override
	public void delete(Long id) {
		medicoDAO.deleteById(id);
	}

	@Override
	public void deleteLogic(Medico t) throws CustomServiceException {
		
	}

	@Transactional
	@Override
	public Medico saveCustom(MedicoDTO medicoDTO) throws CustomServiceException {
		/*Trasaccion 1 registrar persona*/
		Persona persona = new Persona();
		persona = convertToEntity(medicoDTO.getPersonaDTO());
		persona.setFechaRegistro(LocalDateTime.now());
		persona.setEstado(Constant.STATUS_ENABLE);
		personaDAO.save(persona);
		
		/*Transaccion 2 registrar medico*/
		Especialidad especialidad = new Especialidad();
		especialidad = convertToEntity(medicoDTO.getEspecialidadDTO());
		
		Medico medico = new Medico();
		medico = convertToEntityMedico(medicoDTO);
		medico.setPersona(persona);
		medico.setEspecialidad(especialidad);
		medico.setFechaReg(LocalDate.now());
		medico.setEstado(Constant.STATUS_ENABLE);
		
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

}
