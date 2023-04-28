package com.sulcacorp.lissa.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sulcacorp.lissa.entity.Rol;
import com.sulcacorp.lissa.entity.Usuario;
import com.sulcacorp.lissa.entity.UsuarioRol;
import com.sulcacorp.lissa.security.entity.UserDetailsImpl;
import com.sulcacorp.lissa.security.repository.IUsuarioRepository;
import com.sulcacorp.lissa.security.repository.IUsuarioRolRepository;

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
		
		if(!repository.existsByNombreUsuario(username)) {
			new UsernameNotFoundException("El usuario " + username + "No existe.");
		}
		
		Usuario usuario = repository.findByNombre(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + "No existe."));
		
		
		
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/*Construir nuevo userDetails*/
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		userDetailsImpl.setUsuario(usuario.getNombreUsuario());
		userDetailsImpl.setContrasenia(usuario.getContrasenia());
		userDetailsImpl.setRoles(authorities);

		return userDetailsImpl;

	}

}
