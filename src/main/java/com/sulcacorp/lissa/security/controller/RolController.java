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
import com.sulcacorp.lissa.entity.Rol;
import com.sulcacorp.lissa.security.service.impl.RolServiceimpl;
import com.sulcacorp.lissa.util.Constante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "api/rol")
public class RolController extends GenericController{
	
	@Autowired
	private RolServiceimpl rolService;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info("Inicio RolController findAll");
		try {
			List<Rol> list = rolService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (Exception e) {
			log.error("Error RolController findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info("Inicio RolController findById");
		Rol rol = new Rol();
		try {
			rol = rolService.findById(id);
			if(rol == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(rol);
		} catch (Exception e) {
			log.error("Error RolController findById :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody Rol rol, BindingResult result){
		log.info("Inicio RolController save");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			Rol dto = rolService.save(rol);			
			return this.getCreatedResponse(dto,result);
		} catch (Exception e) {
			log.error("Error RolController save :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody Rol rol, BindingResult result){
		log.info("Inicio RolController update");
		try {
			if(result.hasErrors()) {
				return this.getBadRequest(result);
			}
			Rol rolDTO = rolService.findById(rol.getIdRol());
			if(rolDTO == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseRegistro(rolService.update(rol), result);
		} catch (Exception e) {
			log.error("Error RolController update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}

	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info("Inicio RolController delete");
		Rol obj = new Rol();
		try {
			obj = rolService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			rolService.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (Exception e) {
			log.error("Error RolController delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}

}
