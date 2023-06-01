package com.sulcacorp.lissa.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.entity.UsuarioRol;
import com.sulcacorp.lissa.security.repository.IUsuarioRolRepository;
import com.sulcacorp.lissa.security.service.IUsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService{
	
	@Autowired
	private IUsuarioRolRepository repository;

	@Override
	public UsuarioRol save(UsuarioRol t) throws Exception {
		return repository.save(t);
	}

	@Override
	public UsuarioRol update(UsuarioRol t) throws Exception {
		return repository.save(t);
	}

	@Override
	public UsuarioRol findById(Long id) throws Exception {
		Optional<UsuarioRol> op = repository.findById(id);
		return op.isPresent() ? op.get():null;
	}
	
	@Override
	public List<UsuarioRol> findAll() throws Exception {
		return repository.findAllAct();
	}

	@Override
	public List<UsuarioRol> findAllAct() throws Exception {
		return repository.findAllAct();
	}

	@Override
	public void delete(Long id) throws Exception {
		repository.deleteById(id);
		
	}
}
