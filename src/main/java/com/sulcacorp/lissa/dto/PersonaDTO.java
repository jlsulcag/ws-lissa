package com.sulcacorp.lissa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sulcacorp.lissa.entity.EstadoCivil;
import com.sulcacorp.lissa.entity.TipoDocumento;
import com.sulcacorp.lissa.entity.TipoPersona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)//Ignora campos desconocidos
@JsonInclude(JsonInclude.Include.NON_NULL)//los valores nulos no forman parte de la salida json final
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {	
	private Long idPersona;
	
	@NotNull(message = "El Tipo de Persona no puede ser nulo")
	private TipoPersona tipoPersona;
	
	@NotNull(message = "El Tipo de Documento no puede ser nulo")
	private TipoDocumento tipoDocumento;
	
	private EstadoCivil estadoCivil;
	
	@NotNull(message = "El Nombre no puede ser nulo")
	@Size(min = 1, message = "El Nombre es requerido")
	private String nombres;	
	
	@NotNull(message = "El Apellido Paterno no puede ser nulo")
	@Size(min = 1, message = "El Apellido Paterno es requerido")
	private String apellidoPaterno;	
	
	@NotNull(message = "El Apellido Materno no puede ser nulo")
	@Size(min = 1, message = "El Apellido Materno es requerido")
	private String apellidoMaterno;	
	
	@NotNull(message = "El Numero de Documento no puede ser nulo")
	@Size(min = 8, message = "El Numero de Documento es requerido y debe tener minimo {min} caracteres")
	private String numeroDocumentoIdentidad;
	
	//ISODATE 2020-10-01//Formato Universal de fechas
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate fechaNacimiento;	
		
	//ISODATE 2020-10-01T05:00:00.000Z//Formato Universal de fechas
	@JsonSerialize(using = ToStringSerializer.class)
	@NotNull
	private LocalDateTime fechaRegistro;
	
	private String sexo;	
	private String direccion;	
	private String telefono;	
	private String correoElectronico;
	
	@NotNull(message = "El Estado no puede ser nulo")
	@Size(min = 1, message = "El Estado debe tener {min} carácter")
	private String estado;
}
