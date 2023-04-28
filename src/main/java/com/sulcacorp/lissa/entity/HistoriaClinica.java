package com.sulcacorp.lissa.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "HISTORIA_CLINICA")
public class HistoriaClinica {
	@Id
	@Column(name = "ID_HISTORIA_CLINICA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idhistoriaclinica;
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false, foreignKey = @ForeignKey(name = "FK_PERSONA"))
	private Persona persona;
	@Column(name = "NUMEROHC", nullable = false)
	private long numerohc;
	@Column(name = "FECHA_REGISTRO", nullable = false)
	private LocalDate fechaRegistro;
	@Column(name="ESTADO", nullable = false)
	private int estado;
}
