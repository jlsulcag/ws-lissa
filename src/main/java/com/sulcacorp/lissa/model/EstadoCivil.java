package com.sulcacorp.lissa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ESTADO_CIVIL")
public class EstadoCivil {
	@Id
	@Column(name = "ID_ESTADO_CIVIL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEstadoCivil;
	
	@Size(min = 3, message = "Estado Civil : debe tener un minimo de 3 caracteres")
	@Column(name = "DESCRIPCION", nullable = false, length = 20)
	private String descripcion;
		
	@Column(name = "ESTADO", nullable = false)
	private int estado;

	
	

}
