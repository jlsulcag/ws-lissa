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

import com.sulcacorp.lissa.exception.ModeloNotFoundException;
import com.sulcacorp.lissa.model.EstadoCivil;
import com.sulcacorp.lissa.model.TipoDocumento;
import com.sulcacorp.lissa.model.TipoPersona;
import com.sulcacorp.lissa.service.ITipoDocumentoService;

@RestController
@RequestMapping("/api")
public class TipoDocumentoController {
	
	private static Logger log = LoggerFactory.getLogger(PersonaController.class);	
	
	@Autowired
	private ITipoDocumentoService service;
	
	
	@PostMapping(value = "/tipodocumento/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@Valid @RequestBody TipoDocumento t){
		t.setAbreviatura(t.getAbreviatura().toUpperCase());
		t.setDescripcion(t.getDescripcion().toUpperCase());
		return new ResponseEntity<Object>(service.guardar(t), HttpStatus.OK);
	}
	
	@GetMapping(value = "/tipodocumento/list/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoDocumento>> listar(){
		return new ResponseEntity<List<TipoDocumento>>(service.listar(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/tipodocumento/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody TipoDocumento t) {
		t.setAbreviatura(t.getAbreviatura().toUpperCase());
		t.setDescripcion(t.getDescripcion().toUpperCase());
		service.actualizar(t);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/tipodocumento/delete/{id}")
	public void eliminar(@PathVariable("id") long id) {
		TipoDocumento obj = service.buscar(id);
		if(obj == null || obj.getIdTipoDocumento() == 0) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}else {
			service.eliminar(id);
		}		
	}
}
