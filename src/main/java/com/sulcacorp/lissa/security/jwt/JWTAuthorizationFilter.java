package com.sulcacorp.lissa.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getToken(request);
			if(token != null && tokenUtils.validateToken(token)) {
				UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(usernamePAT);
			}
		} catch (Exception e) {
			log.error("Error JWTAuthorizationFilter doFilterInternal()");
		}

		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			return token;
		}else {
			return null;
		}

	}

}
