package com.sulcacorp.lissa.controller;

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
import com.sulcacorp.lissa.dto.PersonaDTO;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.service.impl.PersonaServiceImpl;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/persona")
public class PersonaController extends GenericController{

	@Autowired
	private PersonaServiceImpl service;
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<ResponseModel> findAll(){
		log.info(">>> Execute findAll Persona");
		try {
			List<PersonaDTO> list = service.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error(">>> Error findAll Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error findAll Persona : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info(">>> Execute findById Persona");
		PersonaDTO persona = new PersonaDTO();
		try {
			persona = service.findById(id);
			if(persona == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(persona);
		} catch (CustomServiceException e) {
			log.error(">>> Error findById Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error findById Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody PersonaDTO persona, BindingResult result){
		log.info(">>> Execute save Persona");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {			
			return this.getCreatedResponse(service.save(persona),result);
		} catch (CustomServiceException e) {
			log.error(">>> Error save Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error save Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody PersonaDTO persona, BindingResult result){
		log.info(">>> Execute update Persona");
		try {
			PersonaDTO temp = service.findById(persona.getIdPersona());
			if(temp == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseRegistro(service.update(persona), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error update Persona : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error update Persona : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info(">>> Execute delete Persona");
		PersonaDTO obj = new PersonaDTO();
		try {
			obj = service.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			service.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error(">>> Error delete Persona : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error delete Persona : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
		
	}
	
	@GetMapping(value = "/findByTipoDocAndNumero/{tipoDoc}/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findByTipoDocAndNumero(@PathVariable("tipoDoc") Long typeDoc, @PathVariable("numero") String docNumber){
		log.info(">>> Execute findByTipoDocAndNumero Persona");
		PersonaDTO persona = new PersonaDTO();
		try {
			persona = service.buscarXDoc(typeDoc, docNumber);
			if(persona == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(persona);
		} catch (CustomServiceException e) {
			log.error(">>> Error findByTipoDocAndNumero Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error findByTipoDocAndNumero Persona :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
}
