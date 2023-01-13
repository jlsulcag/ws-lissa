package com.sulcacorp.lissa.security.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulcacorp.lissa.security.entity.UserDetailsImpl;
import com.sulcacorp.lissa.security.request.AuthRequest;

public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{

	/*Filtro de intento de authentication*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {


		AuthRequest authRequest = new AuthRequest();
		
		try {
			authRequest = new ObjectMapper().readValue(request.getReader(), AuthRequest.class);
		} catch (IOException e) {
		}
		
		UsernamePasswordAuthenticationToken userNamePAT = new UsernamePasswordAuthenticationToken(
				authRequest.getUsuario(), 
				authRequest.getContrasenia(), 
				Collections.emptyList());
		
		
		return getAuthenticationManager().authenticate(userNamePAT);
	}
	
	
	/*if authentication is seccessfull*/
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, 			
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
		UserDetailsImpl userDetails = (UserDetailsImpl)authResult.getPrincipal();
		
		String token = TokenUtils.createToken(userDetails.getUsername());
		
		response.addHeader("Authorization", "Bearer "+token);
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
