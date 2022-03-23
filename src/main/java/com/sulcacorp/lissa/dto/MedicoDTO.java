package com.sulcacorp.lissa.dto;

import javax.validation.Valid;

import com.sulcacorp.lissa.model.TipoMedico;

import lombok.Data;

@Data
public class MedicoDTO {

	/*Usar la anotacion para  validacion de objetos en cascada*/
	@Valid
	private PersonaDTO personaDTO;
	
	@Valid
	private EspecialidadDTO especialidadDTO;
	
	private TipoMedico tipoMedico;
	
	private String colegiatura;
	
	private String fechaReg;
	
	private String estado;
	

}
