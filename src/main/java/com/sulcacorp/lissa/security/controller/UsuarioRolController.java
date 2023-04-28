package com.sulcacorp.lissa.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.sulcacorp.lissa.entity.UsuarioRol;
import com.sulcacorp.lissa.security.service.impl.UsuarioRolServiceImpl;
import com.sulcacorp.lissa.util.Constante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "api/usuario-rol")
public class UsuarioRolController extends GenericController{
	
	@Autowired
	private UsuarioRolServiceImpl usuarioRolService;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info("Inicio UsuarioRolController findAll");
		try {
			List<UsuarioRol> list = usuarioRolService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (Exception e) {
			log.error("Error UsuarioRolController findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info("Inicio UsuarioRolController findById");
		UsuarioRol usuarioRol = new UsuarioRol();
		try {
			usuarioRol = usuarioRolService.findById(id);
			if(usuarioRol == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(usuarioRol);
		} catch (Exception e) {
			log.error("Error UsuarioRolController findById :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody UsuarioRol usuarioRol, BindingResult result){
		log.info("Inicio UsuarioRolController save");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			UsuarioRol dto = usuarioRolService.save(usuarioRol);			
			return this.getCreatedResponse(dto,result);
		} catch (Exception e) {
			log.error("Error UsuarioRolController save :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody UsuarioRol usuarioRol, BindingResult result){
		log.info("Inicio UsuarioRolController update");
		try {
			if(result.hasErrors()) {
				return this.getBadRequest(result);
			}
			UsuarioRol rolDTO = usuarioRolService.findById(usuarioRol.getIdUsuarioRol());
			if(rolDTO == null) {
				return this.getNotFoundRequest();
			}			
			return this.getOkResponseRegistro(usuarioRolService.update(usuarioRol), result);
		} catch (Exception e) {
			log.error("Error UsuarioRolController update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info("Inicio UsuarioRolController delete");
		UsuarioRol obj = new UsuarioRol();
		try {
			obj = usuarioRolService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			usuarioRolService.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (Exception e) {
			log.error("Error UsuarioRolController delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}

}
