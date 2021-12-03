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
import com.sulcacorp.lissa.model.TipoPersona;
import com.sulcacorp.lissa.service.ITipoPersonaService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tipoPersona")
public class TipoPersonaController extends GenericController{
	
	@Autowired
	private ITipoPersonaService service;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAllAct(){
		log.info(">>> Execute findAll {}", TipoPersona.class.getName());
		try {
			List<TipoPersona> list = service.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(list);
		} catch (CustomServiceException e) {
			log.error(">>> Error execute findAll {}", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info(">>> Execute findById {}", TipoPersona.class.getName());
		try {
			TipoPersona obj = service.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error(">>> Error execute findById {}", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody TipoPersona obj, BindingResult result){
		log.info(">>> Execute save {} ", TipoPersona.class.getName());
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			return this.getCreatedResponse(service.save(obj), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error save {} ", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
		
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody TipoPersona obj, BindingResult result){
		log.info(">>> Execute update {} ", TipoPersona.class.getName());
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			TipoPersona temp = service.findById(obj.getIdTipoPersona());
			if(temp == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseRegistro(service.save(obj), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error update {} ", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
		
	}
	
	@PutMapping(value = "/updateStatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> updateStatus(@PathVariable("id") Long id){
		log.info(">>> Execute updateStatus {} ", TipoPersona.class.getName());
		try {
			TipoPersona temp = service.findById(id);
			if(temp == null) {
				return this.getNotFoundRequest();
			}
			temp.setEstado(Constant.STATUS_DISABLE);
			return this.getOkResponseConsulta(service.save(temp));
		} catch (CustomServiceException e) {
			log.error(">>> Error updateStatus {} ", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
		
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id){
		log.info(">>> Execute delete {} ", TipoPersona.class.getName());
		try {
			TipoPersona temp = service.findById(id);
			if(temp == null) {
				return this.getNotFoundRequest();
			}
			service.delete(id);
			return this.getOkResponseConsulta(null);
		} catch (CustomServiceException e) {
			log.error(">>> Error delete {} ", TipoPersona.class.getName());
			return this.getInternalServerError();
		}
		
	}
}
