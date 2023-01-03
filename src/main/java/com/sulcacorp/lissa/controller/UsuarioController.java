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
import com.sulcacorp.lissa.model.Usuario;
import com.sulcacorp.lissa.service.IUsuarioService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends GenericController{

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findAll(){
		log.info("Inicio UsuarioController findAll");
		try {
			List<Usuario> list = usuarioService.findAllAct();
			if(list.isEmpty()) {
				return this.getNotFoundRequest();
			}	
			return this.getOkResponseConsulta(list);			
		} catch (CustomServiceException e) {
			log.error("Error UsuarioController findAll :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error UsuarioController findAll : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
		log.info("Process findById");
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioService.findById(id);
			if(usuario == null) {
				return this.getNotFoundRequest();
			}
			return getOkResponseConsulta(usuario);
		} catch (CustomServiceException e) {
			log.error("Error usuario findById :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error usuario update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
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
		} catch (CustomServiceException e) {
			log.error("Error usuario save :\n {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error usuario save : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
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
		} catch (CustomServiceException e) {
			log.error("Error usuario update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error usuario update : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
		log.info("Process delete");
		Usuario obj = new Usuario();
		try {
			obj = usuarioService.findById(id);
			if(obj == null) {
				return this.getNotFoundRequest();
			}			
			usuarioService.delete(id);
			return this.getOkResponseConsulta(obj);
		} catch (CustomServiceException e) {
			log.error("Error usuario delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		} catch (Exception e) {
			log.error("Error usuario delete : {}", e.fillInStackTrace());
			return this.getInternalServerError(Constant.ERROR_500);
		}
		
	}
}
