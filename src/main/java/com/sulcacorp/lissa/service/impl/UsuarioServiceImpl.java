package com.sulcacorp.lissa.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.repository.IUsuarioRepository;
import com.sulcacorp.lissa.service.IUsuarioService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository repository;

	@Override
	public Usuario save(Usuario t) throws CustomServiceException {
		t.setEstado(Constant.STATUS_REG_ENABLE);
		t.setFechaReg(LocalDateTime.now());
		t.setNombreUsuario(t.getNombreUsuario().toUpperCase().trim());
		
		return repository.save(t);
	}

	@Override
	public Usuario update(Usuario t) throws CustomServiceException {
		t.setEstado(t.getEstado().toUpperCase());
		t.setFechaReg(t.getFechaReg());
		t.setNombreUsuario(t.getNombreUsuario().toUpperCase().trim());
		
		return repository.save(t);
	}

	@Override
	public Usuario findById(Long id) throws CustomServiceException {
		Optional<Usuario> op = repository.findById(id);
		return op.isPresent() ? op.get() : null;
	}

	@Override
	public List<Usuario> findAllAct() throws CustomServiceException {		
		return repository.findAllAct();
	}

	@Override
	public void delete(Long id) throws CustomServiceException {
		repository.deleteById(id);
		
	}

	@Override
	public void deleteLogic(Usuario t) throws CustomServiceException {
		// TODO Auto-generated method stub
		
	}

}
