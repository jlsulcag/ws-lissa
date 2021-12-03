package com.sulcacorp.lissa.model;

import java.time.LocalDate;

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
@Table(name = "SEGURO")
public class Seguro {
	@Id
	@Column(name = "ID_SEGURO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSeguro;
	@Column(name = "DESCRIPCION", nullable = false, length = 120)
	private String descripcion;
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDate fechaReg;
	@Column(name = "IS_CONVENIO", nullable = false)
	private int isConvenio;
	@Column(name = "TIENE_CARTA", nullable = false)
	private int tieneCarta;
	@Column(name = "ESTADO", nullable = false)
	private int estado;
}
