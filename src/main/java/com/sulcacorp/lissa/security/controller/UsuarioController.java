package com.sulcacorp.lissa.security.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.entity.Usuario;
import com.sulcacorp.lissa.security.dto.JwtDto;
import com.sulcacorp.lissa.security.jwt.TokenUtils;
import com.sulcacorp.lissa.security.request.AuthRequest;
import com.sulcacorp.lissa.security.request.UsuarioRequest;
import com.sulcacorp.lissa.security.service.IUsuarioService;
import com.sulcacorp.lissa.util.Constante;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends GenericController{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private AuthenticationManager authenticationManager;	
	
	@PostMapping(value = "/login")
	public ResponseEntity<ResponseModel>  login(@Valid @RequestBody AuthRequest authRequest, BindingResult bindingResult){
		log.info("Inicio UsuarioController login() - Obtener usuario, roles y token");
		
		try {
			
			if(bindingResult.hasErrors()) {
				return this.getBadRequest(bindingResult);
			}
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsuario(), authRequest.getContrasenia()));			
						
			if(!authentication.isAuthenticated()) {
				return this.getNotFoundRequest();
			}
			
			SecurityContextHolder.getContext().setAuthentication(authentication);			
			String token = TokenUtils.createToken(authRequest.getUsuario());			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
			log.info("userDetails.getAuthorities() "+userDetails.getAuthorities());
			JwtDto jwtDto = new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities());
			
			log.info("Fin UsuarioController login()");
			return this.getOkResponseRegistro(jwtDto, bindingResult);
		} catch (BadCredentialsException e) {
			log.info("Error UsuarioController login()  BadCredentialsException {} ",e.fillInStackTrace());
			return this.getNotFoundRequest();
		} catch (Exception e) {
			log.error("Error UsuarioController login()  {} ",e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
		
		
		
	}
	
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info("Inicio UsuarioController findAll");
		try {
			List<Usuario> list = usuarioService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (Exception e) {
			log.error("Error UsuarioController findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info("Inicion UsuarioController findById");
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioService.findById(id);
			if(usuario == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(usuario);
		} catch (Exception e) {
			log.error("Error UsuarioController findById :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody Usuario usuario, BindingResult result){
		log.info("Inicio UsuarioController save");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			Usuario user = usuarioService.save(usuario);
			log.info("Fin UsuarioController save");
			return this.getCreatedResponse(user,result);
		} catch (Exception e) {
			log.error("Error UsuarioController save :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody Usuario usuario, BindingResult result){
		log.info("Inicio UsuarioController update");
		try {
			if(result.hasErrors()) {
				return this.getBadRequest(result);
			}
			Usuario usuarioDTO = usuarioService.findById(usuario.getIdUsuario());
			if(usuarioDTO == null) {
				return this.getNotFoundRequest();
			}
			log.info("Fin UsuarioController update");
			return this.getOkResponseRegistro(usuarioService.update(usuario), result);
		} catch (Exception e) {
			log.error("Error UsuarioController update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info("Inicio UsuarioController delete");
		Usuario obj = new Usuario();
		try {
			obj = usuarioService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			usuarioService.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (Exception e) {
			log.error("Error UsuarioController delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> createUser(@Valid @RequestBody UsuarioRequest usuarioRequest, BindingResult result){
		Integer response;
		log.info("Inicio UsuarioController createUser ");
		try {
			
			if(result.hasErrors()) {
				log.info("Fail request");
				return this.getBadRequest(result);
			}
			
			if(usuarioService.existsByNombreUsuario(usuarioRequest.getNombreUsuario())) {
				log.info("El usuario ya existe");
				result.addError(new ObjectError("error", "El usuario ya existe"));
				return this.getBadRequest(result);				
			}
			
			response = usuarioService.createUser(usuarioRequest);
			if(response == Constante.STATUS_SUCCESS) {
				return this.getCreatedResponseTransactional();
			}
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error("Error UsuarioRolController createUser :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	
}
