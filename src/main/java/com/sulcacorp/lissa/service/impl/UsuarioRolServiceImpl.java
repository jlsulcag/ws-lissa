package com.sulcacorp.lissa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.UsuarioRol;
import com.sulcacorp.lissa.repository.IUsuarioRolRepository;
import com.sulcacorp.lissa.service.IUsuarioRolService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService{
	
	@Autowired
	private IUsuarioRolRepository repository;

	@Override
	public UsuarioRol save(UsuarioRol t) throws CustomServiceException {
		return repository.save(t);
	}

	@Override
	public UsuarioRol update(UsuarioRol t) throws CustomServiceException {
		return repository.save(t);
	}

	@Override
	public UsuarioRol findById(Long id) throws CustomServiceException {
		Optional<UsuarioRol> op = repository.findById(id);
		return op.isPresent() ? op.get():null;
	}

	@Override
	public List<UsuarioRol> findAllAct() throws CustomServiceException {
		return repository.findAllAct();
	}

	@Override
	public void delete(Long id) throws CustomServiceException {
		repository.deleteById(id);
		
	}

	@Override
	public void deleteLogic(UsuarioRol t) throws CustomServiceException {
		// TODO Auto-generated method stub
		
	}
	
	

}
