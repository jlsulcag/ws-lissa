package com.sulcacorp.lissa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sulcacorp.lissa.security.jwt.JWTAuthenticationFilter;
import com.sulcacorp.lissa.security.jwt.JWTAuthorizationFilter;
import com.sulcacorp.lissa.security.service.impl.UsuarioDetailServiceImpl;

import lombok.AllArgsConstructor;


//@EnableWebSecurity //configuración de seguridad web
//@EnableGlobalMethodSecurity(prePostEnabled = true) //Proteger toda la aplicación a nivel global
@AllArgsConstructor
@Configuration
public class SecurityConfig{
	
	@Autowired
	@Qualifier("usuarioDetailServiceImpl")
	private UsuarioDetailServiceImpl usuarioDetailServiceImpl;
	
	@Autowired
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		
		return http
		.csrf()
		.disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(jwtAuthenticationFilter)
		.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}
	
	/*
	@Bean
	UserDetailsService userDetailService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin")
				.password(passwordEncoder().encode("admin"))
				.roles()
				.build());
		
		return manager;
	}
	*/	
	
	@Bean
	AuthenticationManager autManager(HttpSecurity http, PasswordEncoder passEncoder) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(usuarioDetailServiceImpl)
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
	

