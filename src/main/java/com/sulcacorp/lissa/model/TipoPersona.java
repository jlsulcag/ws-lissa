package com.sulcacorp.lissa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TIPO_PERSONA")
public class TipoPersona {
	@Id
	@Column(name = "ID_TIPO_PERSONA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipoPersona;
	
	@Column(name = "DESCRIPCION", nullable = false, length = 60)
	private String descripcion;
	
	@Column(name = "ESTADO", nullable = false)
	private int estado;

}
