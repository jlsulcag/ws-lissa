package com.sulcacorp.lissa.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(value = "/especialidad/save")
	public ResponseEntity<Especialidad> save(@Valid @RequestBody Especialidad especialidad){
		return ResponseEntity.ok(especialidadService.guardar(especialidad));
	}
	
	@PutMapping(value = "/especialidad/update")
	public ResponseEntity<Especialidad> update(@Valid @RequestBody Especialidad especialidad){
		return ResponseEntity.ok(especialidadService.guardar(especialidad));
	}
	
	@DeleteMapping(value = "/especialidad/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		Especialidad obj = new Especialidad();
		obj = especialidadService.buscar(id);
		if(obj != null && obj.getIdEspecialidad() != null) {
			especialidadService.eliminar(id);
		}else {
			throw new ModeloNotFoundException(Especialidad.class.getSimpleName() + " no existe");
		}
	}
}
