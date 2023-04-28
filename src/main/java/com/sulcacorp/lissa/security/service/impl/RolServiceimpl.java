package com.sulcacorp.lissa.security.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.Rol;
import com.sulcacorp.lissa.security.repository.IRolRepository;
import com.sulcacorp.lissa.security.service.IRolService;

@Service
public class RolServiceimpl implements IRolService{
	
	@Autowired
	private IRolRepository repository;

	@Override
	public Rol save(Rol t) throws Exception {
		t.setFechaReg(LocalDateTime.now());
		t.setNombreRol(t.getNombreRol().toUpperCase());
		return repository.save(t);
	}

	@Override
	public Rol update(Rol t) throws Exception {
		t.setFechaReg(t.getFechaReg());
		t.setNombreRol(t.getNombreRol().toUpperCase());
		return repository.save(t);
	}

	@Override
	public Rol findById(Long id) throws Exception {
		Optional<Rol> op = repository.findById(id);
		return op.isPresent() ? op.get(): null;
	}

	@Override
	public List<Rol> findAll() throws Exception {
		return repository.findAllAct();
	}
	
	@Override
	public List<Rol> findAllAct() throws Exception {
		return repository.findAllAct();
	}

	@Override
	public void delete(Long id) throws Exception {
		repository.deleteById(id);
		
	}

	@Override
	public void deleteLogic(Rol t) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
