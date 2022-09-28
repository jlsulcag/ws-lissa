package com.sulcacorp.lissa.dto;

import java.time.LocalDate;

import javax.validation.Valid;

import com.sulcacorp.lissa.model.TipoMedico;

import lombok.Data;

@Data
public class MedicoDTO {

	private Long id;
	/*Usar la anotacion para  validacion de objetos en cascada*/
	@Valid
	private PersonaDTO personaDTO;
	
	//@Valid
	private EspecialidadDTO especialidadDTO;
	
	private TipoMedico tipoMedico;
	
	private String colegiatura;
	
	private LocalDate fechaReg;
	
	private String estado;
	
	

}
