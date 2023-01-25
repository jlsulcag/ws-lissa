package com.sulcacorp.lissa.security.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sulcacorp.lissa.model.Persona;
import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.model.UsuarioRol;
import com.sulcacorp.lissa.repository.IPersonaDAO;
import com.sulcacorp.lissa.security.repository.IUsuarioRepository;
import com.sulcacorp.lissa.security.repository.IUsuarioRolRepository;
import com.sulcacorp.lissa.security.request.UsuarioRequest;
import com.sulcacorp.lissa.security.service.IUsuarioService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private Integer responseStatus;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioRepository repository;

	@Autowired
	private IPersonaDAO repositoryPersona;

	@Autowired
	private IUsuarioRolRepository repositoryUsuarioRol;


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

	@Override
	@Transactional
	public Integer createUser(UsuarioRequest usuarioRequest) throws CustomServiceException {

		try {
			Persona temp = new Persona();

			Optional<Persona> op = repositoryPersona.findById(usuarioRequest.getPersona().getIdPersona());
			if (op.isPresent()) {
				temp = op.get();
			} else {
				temp = null;
			}
			/* Reg User */
			Usuario user = new Usuario();
			user.setNombreUsuario(usuarioRequest.getNombreUsuario());
			user.setContrasenia(passwordEncoder.encode(usuarioRequest.getContrasenia()));
			user.setPersona(temp);

			this.save(user);

			/* Reg Usuario Rol */
			usuarioRequest.getRoles().forEach(rol -> {
				UsuarioRol usuarioRolTemp = new UsuarioRol();
				usuarioRolTemp.setUsuario(user);
				usuarioRolTemp.setRol(rol);

				repositoryUsuarioRol.save(usuarioRolTemp);
			});

			responseStatus = Constant.STATUS_SUCCESS;
		} catch (Exception e) {
			log.info("Error UsuarioServiceImpl create User {} ", e.fillInStackTrace());
			responseStatus = Constant.STATUS_ERROR;
		}

		return responseStatus;
	}

	@Override
	public boolean existsByNombreUsuario(String nombreUsuario) throws CustomServiceException {
		return repository.existsByNombreUsuario(nombreUsuario);
	}



}
