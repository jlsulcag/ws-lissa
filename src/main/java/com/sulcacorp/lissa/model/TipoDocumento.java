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
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento {
	@Id
	@Column(name = "ID_TIPO_DOCUMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipoDocumento;
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	
	@Column(name = "ABREVIATURA" , length = 10)
	private String abreviatura;
	
	@Column(name = "CODIGO" , length = 2)
	private String codigo;
	
	@Column(name = "ESTADO", nullable = false)
	private int estado;
	
	

}
