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
import com.sulcacorp.lissa.dto.EspecialidadDTO;
import com.sulcacorp.lissa.service.IEspecialidadService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController extends GenericController{

	@Autowired
	private IEspecialidadService especialidadService;
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<ResponseModel> findAll(){
		log.info(">>> Process findAll");
		try {
			List<EspecialidadDTO> list = especialidadService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad findAll :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@GetMapping(value = "/findById/{id}")
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
			log.error(">>> Error especialidad findById :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody EspecialidadDTO especialidad, BindingResult result){
		log.info(">>> Process save");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			EspecialidadDTO dto = especialidadService.save(especialidad);			
			return this.getCreatedResponse(dto,result);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad findById :\n {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@PutMapping(value = "/update")
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
			especialidad.setEstado(especialidadDTO.getEstado());
			especialidad.setFechaReg(especialidadDTO.getFechaReg());
			return this.getOkResponseRegistro(especialidadService.update(especialidad), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad update : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		} catch (Exception e) {
			log.error(">>> Error especialidad update : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
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
			log.error(">>> Error especialidad delete : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
		
	}
	
	@PutMapping(value = "/updateStatus/{id}")
	public ResponseEntity<ResponseModel> updateStatus(@PathVariable("id") Long id) {
		log.info(">>> Process updateStatus");
		try {
			EspecialidadDTO obj = especialidadService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(especialidadService.update(obj));
		} catch (CustomServiceException e) {
			log.error(">>> Error especialidad updateStatus : {}", e.getMessage());
			return this.getInternalServerError(e.getMessage());
		}
		
	}
}
