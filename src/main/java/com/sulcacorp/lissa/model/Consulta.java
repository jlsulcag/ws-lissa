package com.sulcacorp.lissa.model;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONSULTA")
public class Consulta {
	@Id
	@Column(name = "ID_CONSULTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idConsulta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA_PACIENTE", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULTA_PERSONA"))
	private Persona personaPaciente;
	
	/*
	@ManyToOne
	@JoinColumn(name = "ID_HISTORIA_CLINICA", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULTA_HC"))
	private HistoriaClinica historiaClinica;
	*/
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_ADMISION", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULTA_USUARIOADM"))
	private Usuario usuarioAdmision;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_TRIAJE", nullable = true, foreignKey = @ForeignKey(name="FK_CONSULTA_USUARIOTRI"))
	private Usuario usuarioTriaje;
	
	@ManyToOne
	@JoinColumn(name = "ID_CITA", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULTA_CITA"))
	private Cita cita;
	
	@Column(name = "EDAD_CONSULTA_PACIENTE", nullable = true)
	private int edadCONSULTAPaciente;
	
	@Column(name = "FECHA_CONSULTA", nullable = false)
	private LocalDate fechaConsulta;
	
	@Column(name = "NUMERO_CONSULTA")
	private long numeroConsulta;
	
	@Column(name = "TIPO_INGRESO", nullable = false, length = 60)
	private String tipoIngreso; //CONSULTA, REEVALUACION
	
	@Column(name = "ESTADO", nullable = false)
	private int estado;
}
