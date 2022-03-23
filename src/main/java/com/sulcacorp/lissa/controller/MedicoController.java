package com.sulcacorp.lissa.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.sulcacorp.lissa.dto.MedicoDTO;
import com.sulcacorp.lissa.model.Medico;
import com.sulcacorp.lissa.model.view.MedicoView;
import com.sulcacorp.lissa.service.IMedicoService;
import com.sulcacorp.lissa.service.IMedicoViewService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/medico")
public class MedicoController extends GenericController{

	@Autowired
	private IMedicoService service;
	
	@Autowired
	private IMedicoViewService serviceMedicoView;

	@GetMapping(value = "/findAll")
	public ResponseEntity<ResponseModel> findAll(){
		log.info(">>> Execute findAll Medico");
		try {
			List<MedicoView> list = serviceMedicoView.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error(">>> Error findAll Medico :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info(">>> Execute findById Medico");
		MedicoView medico = new MedicoView();
		try {
			medico = serviceMedicoView.findById(id);
			if(medico == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(medico);
		} catch (CustomServiceException e) {
			log.error(">>> Error findById Medico :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody MedicoDTO medicoDTO, BindingResult result){
		log.info(">>> Execute save Medico");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			return this.getCreatedResponse(service.saveCustom(medicoDTO),result);
		} catch (CustomServiceException e) {
			log.error(">>> CustomServiceException save Medico :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		} catch (Exception e) {
			log.error(">>> Exception save Medico :\n {}", e.getMessage());
			e.printStackTrace();
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody Medico medico, BindingResult result){
		log.info(">>> Execute update Medico");
		try {
			Medico temp = service.findById(medico.getId());
			if(temp == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseRegistro(service.update(medico), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error update Medico : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info(">>> Execute delete Medico");
		Medico obj = new Medico();
		try {
			obj = service.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			service.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error(">>> Error delete Medico : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
		
	}
	
}
