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
import com.sulcacorp.lissa.model.TipoDocumento;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.service.impl.TipoDocumentoServiceImpl;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tipodocumento")
@Slf4j
public class TipoDocumentoController extends GenericController{
	
	@Autowired
	private TipoDocumentoServiceImpl service;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info(">>> Process findAll");
		try {
			List<TipoDocumento> list = service.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(list);
		} catch (CustomServiceException e) {
			log.error(">>> Error /api/tipodocumento/findAll : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error /api/tipodocumento/findAll : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
		
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") long id){
		log.info(">>> Process /list/findById");
		try {
			TipoDocumento obj = service.findById(id);
			if(obj == null) {
				log.info(">>> TipoDocumento no encontrado");
				return this.getNotFoundRequest();
			}
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error(">>> Error /api/tipodocumento/findById : {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>> Error /api/tipodocumento/findById : {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
		
	}
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> save(@Valid @RequestBody TipoDocumento t,
			BindingResult result){
		log.info(">>> Process /api/tipodocumento/save ");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}
		try {
			TipoDocumento obj = service.save(t);			
			return this.getCreatedResponse(obj, result);
		} catch (CustomServiceException e) {
			log.error(">>>  Error /api/tipodocumento/save : {} ",e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>>  Error /api/tipodocumento/save : {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> update(@Valid @RequestBody TipoDocumento t,
			BindingResult result) {
		log.info(">>> Process /api/tipodocumento/update ");
		if(result.hasErrors()) {
			return this.getBadRequest(result);
		}		
		try {			
			TipoDocumento obj = service.findById(t.getIdTipoDocumento());
			if(obj == null) {
				return this.getNotFoundRequest();
			}
			t.setEstado(obj.getEstado());
			return this.getOkResponseRegistro(service.update(t), result);
		} catch (CustomServiceException e) {
			log.error(">>>  Error /api/tipodocumento/update : {} ",e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>>  Error /api/tipodocumento/update : {}",e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@PutMapping(value = "/updateStatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> updateStatus(@PathVariable("id") Long id) {
		log.info(">>> Process /api/tipodocumento/updateStatus ");		
		try {			
			TipoDocumento obj = service.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}	
			obj.setEstado(Constant.STATUS_REG_DISABLE);
			return this.getOkResponseConsulta(service.update(obj));
		} catch (CustomServiceException e) {
			log.error(">>>  Error /api/tipodocumento/updateStatus : {} ",e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error(">>>  Error /api/tipodocumento/updateStatus : {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
		
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") long id) {
		log.info(">>> Process delete ");
		try {
			TipoDocumento obj = service.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}
			service.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error("Error delete {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error delete {} ", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
			
	}
}
