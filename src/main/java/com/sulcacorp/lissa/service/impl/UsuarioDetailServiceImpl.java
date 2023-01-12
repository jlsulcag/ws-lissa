package com.sulcacorp.lissa.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sulcacorp.lissa.model.Rol;
import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.model.UsuarioRol;
import com.sulcacorp.lissa.repository.IUsuarioRepository;
import com.sulcacorp.lissa.repository.IUsuarioRolRepository;
import com.sulcacorp.lissa.service.exception.CustomServiceException;

@Service("usuarioDetailServiceImpl")
public class UsuarioDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepository repository;

	@Autowired
	private IUsuarioRolRepository repositoryUsuarioRol;

	@Autowired
	private RolServiceimpl serviceRol;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<UsuarioRol> listUsuarioRol = new ArrayList<>();
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		Usuario usuario = repository.findByNombre(username);
		if (usuario != null && usuario.getIdUsuario() > 0) {
			listUsuarioRol = repositoryUsuarioRol.listByUsuario(usuario.getIdUsuario());
		}
		if (!listUsuarioRol.isEmpty()) {
			for (UsuarioRol ur : listUsuarioRol) {
				Rol rol;
				try {
					rol = serviceRol.findById(ur.getRol().getIdRol());
					if (rol != null && rol.getIdRol() > 0) {
						authorities.add(new SimpleGrantedAuthority(rol.getNombreRol().trim()));
					}
				} catch (CustomServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return new User(usuario.getNombreUsuario(), usuario.getContrasenia(), authorities);
	
		//return null;
	}

}
