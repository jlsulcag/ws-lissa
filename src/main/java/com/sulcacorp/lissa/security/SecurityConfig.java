package com.sulcacorp.lissa.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
@Configuration
@EnableWebSecurity //configuración de seguridad web
@EnableGlobalMethodSecurity(prePostEnabled = true) //Proteger toda la aplicación a nivel global
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${security.signing-key}")
	private String signingKey;
	
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	
	@Value("${security.security-realm}")
	private String securityRealm;
	
	@Autowired
	private DataSource datSource;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	
	
}
*/
