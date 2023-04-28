package com.sulcacorp.lissa.security.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sulcacorp.lissa.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsImpl  implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1332879213382809243L;
	
	//private final Usuario usuario;
	private String usuario;
	private String contrasenia;
	private Set<? extends GrantedAuthority> roles = new HashSet<>();
	
	public static UserDetailsImpl buildUsuario(Usuario usuario) {
		return null;		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return contrasenia;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
