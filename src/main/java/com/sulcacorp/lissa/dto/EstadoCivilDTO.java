package com.sulcacorp.lissa.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)//Ignora propiedades desconocidas
@JsonInclude(JsonInclude.Include.NON_NULL)//Ignora  campos nulos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCivilDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idEstadoCivil;
	@NotNull
	@Size(min = 3, max = 20, message = "La especialidad es requerida y debe tener mínimo 3 y máximo  20 caracteres")
	private String descripcion;
	@NotNull
	private String estado;
}
