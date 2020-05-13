package com.sulcacorp.lissa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sulcacorp.lissa.exception.ModeloNotFoundException;
import com.sulcacorp.lissa.model.EstadoCivil;
import com.sulcacorp.lissa.service.impl.EstadoCivilServiceImpl;

@RestController
@RequestMapping("/api")
public class EstadoCivilController {
	
	@Autowired
	private EstadoCivilServiceImpl service;
	private static Logger log = LoggerFactory.getLogger(EstadoCivilController.class);
	
	@PostMapping(value = "/estadoCivil/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@Valid   @RequestBody EstadoCivil t){
		EstadoCivil obj = new EstadoCivil();
		t.setDescripcion(t.getDescripcion().toUpperCase());
		obj = service.guardar(t);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getIdEstadoCivil()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/estadoCivil/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody EstadoCivil t){
		t.setDescripcion(t.getDescripcion().toUpperCase());
		log.info("Update : {}", t.getIdEstadoCivil());
		service.actualizar(t);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/estadoCivil/delete/{id}")
	public void eliminar(@PathVariable("id") long id) {
		EstadoCivil obj = service.buscar(id);
		if(obj == null || obj.getIdEstadoCivil() == 0) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}else {
			service.eliminar(id);
		}		
	}
	
	@GetMapping(value = "/estadoCivil/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstadoCivil> buscarXId(@PathVariable("id") long id){
		EstadoCivil estadoCivil = service.buscar(id);
		if(estadoCivil == null || estadoCivil.getIdEstadoCivil()== 0) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
		return new ResponseEntity<EstadoCivil>(estadoCivil, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estadoCivil/findAll")
	public ResponseEntity<List<EstadoCivil>> listar(){
		return new ResponseEntity<List<EstadoCivil>>(service.listar(), HttpStatus.OK);
	}

}
