package com.sulcacorp.lissa.security.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulcacorp.lissa.security.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager AuthenticationManager;
	
	private UsuarioServiceImpl usuarioServiceImpl;
	
	

}
