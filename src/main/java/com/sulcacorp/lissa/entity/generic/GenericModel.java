package com.sulcacorp.lissa.entity.generic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import lombok.Data;

@MappedSuperclass
@Data
public class GenericModel implements Serializable{
	
	private static final long serialVersionUID = -3216499732482401620L;
	
	@Size(min=1, max=1, message="El estado es requerido y debe ser 0 o 1")
	@Column(name="ESTADO")
	private String estado = "1";

}
