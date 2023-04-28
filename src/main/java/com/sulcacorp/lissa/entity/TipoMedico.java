package com.sulcacorp.lissa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonPropertyOrder({"idTipoMedico", "descTipoMedico", "estado"})
@Table(name = "TIPO_MEDICO")
public class TipoMedico {
	@Id
	@Column(name = "ID_TIPO_MEDICO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoMedico;
	
	@Size(min = 3, max = 60, message = "La especialidad es requerida y debe tener mínimo 3 y máximo  60 caracteres")
	@Column(name = "DESC_TIPO_MEDICO", nullable = false, length = 60)
	private String descTipoMedico;
	
	@Column(name = "ESTADO", nullable = false)
	private  String estado;
}
