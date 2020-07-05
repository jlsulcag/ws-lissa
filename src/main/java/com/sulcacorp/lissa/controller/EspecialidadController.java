package com.sulcacorp.lissa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulcacorp.lissa.model.Especialidad;
import com.sulcacorp.lissa.service.impl.EspecialidadServiceImpl;


@RestController
@RequestMapping("/api")
public class EspecialidadController {

	@Autowired
	private EspecialidadServiceImpl especialidadService;
	
	@GetMapping(value = "/especialidad/findAll")
	public ResponseEntity<List<Especialidad>> findAll(){
		return ResponseEntity.ok(especialidadService.listar());
	}
}
