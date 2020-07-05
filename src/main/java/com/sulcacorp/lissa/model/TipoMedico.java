package com.sulcacorp.lissa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIPO_MEDICO")
public class TipoMedico {
	@Id
	@Column(name = "ID_TIPO_MEDICO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoMedico;
	@Column(name = "DESC_TIPO_MEDICO", nullable = false, length = 60)
	private String DescTipoMedico;
	@Column(name = "ESTADO", nullable = false)
	private  int estado;
}
