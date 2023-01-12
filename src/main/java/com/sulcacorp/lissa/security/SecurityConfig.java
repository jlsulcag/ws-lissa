package com.sulcacorp.lissa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.sulcacorp.lissa.service.impl.UsuarioDetailServiceImpl;


//@EnableWebSecurity //configuración de seguridad web
//@EnableGlobalMethodSecurity(prePostEnabled = true) //Proteger toda la aplicación a nivel global
@Configuration
public class SecurityConfig{
	
	@Autowired
	@Qualifier("usuarioDetailServiceImpl")
	private UsuarioDetailServiceImpl usuarioDetailServiceImpl;
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		return http
		.csrf()
		.disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.build();
	}
	
	@Bean
	UserDetailsService userDetailService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin")
				.password(passwordEncoder().encode("admin"))
				.roles()
				.build());
		
		return manager;
	}	
	
	@Bean
	AuthenticationManager autManager(HttpSecurity http, PasswordEncoder passEncoder) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailService())
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		//auth.userDetailsService(usuarioDetailServiceImpl).passwordEncoder(passwordEncoder());
		auth
		.inMemoryAuthentication().withUser("admin")
		.password(passwordEncoder().encode("admin"));
	}
	
	@Autowired
	public void configure(HttpSecurity http) throws Exception{
		http
		.csrf()
		.disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.build();
	}
	*/
}
	

