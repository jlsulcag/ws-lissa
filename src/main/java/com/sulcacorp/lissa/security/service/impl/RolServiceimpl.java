package com.sulcacorp.lissa.security.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.Rol;
import com.sulcacorp.lissa.security.repository.IRolRepository;
import com.sulcacorp.lissa.security.service.IRolService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;

@Service
public class RolServiceimpl implements IRolService{
	
	@Autowired
	private IRolRepository repository;

	@Override
	public Rol save(Rol t) throws CustomServiceException {
		t.setFechaReg(LocalDateTime.now());
		t.setNombreRol(t.getNombreRol().toUpperCase());
		return repository.save(t);
	}

	@Override
	public Rol update(Rol t) throws CustomServiceException {
		t.setFechaReg(t.getFechaReg());
		t.setNombreRol(t.getNombreRol().toUpperCase());
		return repository.save(t);
	}

	@Override
	public Rol findById(Long id) throws CustomServiceException {
		Optional<Rol> op = repository.findById(id);
		return op.isPresent() ? op.get(): null;
	}

	@Override
	public List<Rol> findAll() throws CustomServiceException {
		return repository.findAllAct();
	}
	
	@Override
	public List<Rol> findAllAct() throws CustomServiceException {
		return repository.findAllAct();
	}

	@Override
	public void delete(Long id) throws CustomServiceException {
		repository.deleteById(id);
		
	}

	@Override
	public void deleteLogic(Rol t) throws CustomServiceException {
		// TODO Auto-generated method stub
		
	}

}
