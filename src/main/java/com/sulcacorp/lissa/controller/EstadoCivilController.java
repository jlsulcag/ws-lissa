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
import com.sulcacorp.lissa.dto.EstadoCivilDTO;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.service.impl.EstadoCivilServiceImpl;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/estadoCivil")
@Slf4j
public class EstadoCivilController extends GenericController {

	@Autowired
	private EstadoCivilServiceImpl service;

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody EstadoCivilDTO t, BindingResult result) {
		log.info("Process estadoCivil save :::>");
		if (result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			EstadoCivilDTO dto;
			dto = service.save(t);
			return this.getCreatedResponse(dto, result);
		} catch (CustomServiceException e) {
			log.error(">>> Error /api/estadoCivil/save {}", e.getMessage());
			return this.getInternalServerError();
		}
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody EstadoCivilDTO t, BindingResult result) {
		log.info("Process estadoCivil actualizar :::>");
		if (result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			EstadoCivilDTO dto = service.findById(t.getIdEstadoCivil());
			if(dto == null) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseRegistro(service.update(t), result);
		} catch (CustomServiceException e) {
			log.error(">>> Error /api/estadoCivil/update {}", e.getMessage());
			return this.getInternalServerError();
		}
	}

	@PutMapping(value = "/deleteLogic/{id}")
	public ResponseEntity<ResponseModel> deleteLogic(@PathVariable("id") long id) {
		log.info(">>> Process estadoCivil eliminar x ID :::>");
		try {
			EstadoCivilDTO dto = service.findById(id);
			if (dto == null) {
				return this.getNotFoundRequest();
			}
			dto.setEstado(Constant.STATUS_DISABLE);			
			return this.getOkResponseConsulta(service.update(dto));
		} catch (CustomServiceException e) {
			log.error(">>> Error estadoCivil delete : {}", e.getMessage());
			return this.getInternalServerError();
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") long id) {
		log.info(">>> Process estadoCivil eliminar x ID :::>");
		try {
			EstadoCivilDTO dto = service.findById(id);
			if (dto == null) {
				log.info(">>> {} no encontrado", this.getClass().getName());
				return this.getNotFoundRequest();
			}			
			service.delete(id);
			return this.getOkResponseConsulta(null);
		} catch (CustomServiceException e) {
			log.error(">>> Error estadoCivil delete : {}", e.getMessage());
			return this.getInternalServerError();
		}
	}

	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") long id) {
		log.info(">>> Process estadoCivil buscar x ID");
		EstadoCivilDTO estadoCivilDTO;
		try {
			estadoCivilDTO = service.findById(id);
			if (estadoCivilDTO == null) {
				log.info("Estado Civil no encontrado :::>");
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(estadoCivilDTO);
		} catch (CustomServiceException e) {
			log.error(">>> Error estadoCivil findById :\n {}", e.getMessage());
			return this.getInternalServerError();
		}

	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<ResponseModel> findAllAct() {
		log.info(">>> Process estadoCivil findAllAct :::>");
		try {
			List<EstadoCivilDTO> list = service.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(list);
		} catch (CustomServiceException e) {
			log.error(">>> Error findAll... {}", e.getMessage());
			return this.getInternalServerError();
		}
		
	}

}
