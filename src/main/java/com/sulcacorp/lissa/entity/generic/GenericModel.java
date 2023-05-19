package com.sulcacorp.lissa.entity.generic;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Data
public class GenericModel implements Serializable{
	
	private static final long serialVersionUID = -3216499732482401620L;
	
	@Size(min=1, max=1, message="El estado es requerido y debe ser 0 ó 1")
	@Column(name="ESTADO", nullable = false, length = 1)
	private String estado = "1";

	/*columnDefinition: Establece valor  por defecto a los registros existentes*/
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "FECHA_REG", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime fechaReg;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "FECHA_ACT")
	private LocalDateTime fechaAct;

}
