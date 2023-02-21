package com.sulcacorp.lissa.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.sulcacorp.lissa.dto.EspecialidadDTO;
import com.sulcacorp.lissa.service.IEspecialidadService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constante;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController extends GenericController{

	@Autowired
	private IEspecialidadService especialidadService;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info(">>> Process findAll");
		try {
			List<EspecialidadDTO> list = especialidadService.findAll();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad findAll : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findAllAct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAllAct(){
		log.info(">>> Process findAll");
		try {
			List<EspecialidadDTO> list = especialidadService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info(">>> Process findById");
		EspecialidadDTO especialidad = new EspecialidadDTO();
		try {
			especialidad = especialidadService.findById(id);
			if(especialidad == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(especialidad);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad findById :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody EspecialidadDTO especialidad, BindingResult result){
		log.info("Inicio  EspecialidadController save");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			boolean es = especialidadService.existsByDescEspecialidad(especialidad.getDescEspecialidad());
			if(especialidadService.existsByDescEspecialidad(especialidad.getDescEspecialidad())){
				return this.getResponseExists();
			}
			EspecialidadDTO dto = especialidadService.save(especialidad);			
			return this.getCreatedResponse(dto,result);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad save :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad save : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody EspecialidadDTO especialidad, BindingResult result){
		log.info(">>> Process update");
		try {
			if(result.hasErrors()) {
				return this.getBadRequest(result);
			}
			EspecialidadDTO especialidadDTO = especialidadService.findById(especialidad.getIdEspecialidad());
			if(especialidadDTO == null) {
				return this.getNotFoundRequest();
			}
			if(especialidadService.existsByDescEspecialidad(especialidad.getDescEspecialidad())){
				return this.getResponseExists();
			}
			especialidad.setEstado(especialidadDTO.getEstado());
			especialidad.setFechaReg(especialidadDTO.getFechaReg());
			return this.getOkResponseRegistro(especialidadService.update(especialidad), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
	}

	
	@PutMapping(value = "/updateStatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> updateStatus(@PathVariable("id") Long id) {
		log.info(">>> Process updateStatus");
		try {
			EspecialidadDTO obj = especialidadService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}
			obj.setEstado(Constante.STATUS_REG_DISABLE);
			return this.getOkResponseConsulta(especialidadService.update(obj));
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad updateStatus : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad updateStatus : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		}
		
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info(">>> Process delete");
		EspecialidadDTO obj = new EspecialidadDTO();
		try {
			obj = especialidadService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			especialidadService.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} catch (DataIntegrityViolationException e) {
			log.error(">>> Error especialidad delete DataIntegrityViolationException {}", e.fillInStackTrace());
			return this.getInternalServerErrorConstraintViolation(Constante.ERROR_COSTRAINT_VIOLATION_500);
		} catch (Exception e) {
			log.error(">>> Error especialidad delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constante.ERROR_500);
		} 
		
	}
}
