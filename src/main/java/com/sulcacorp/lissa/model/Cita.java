package com.sulcacorp.lissa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CITA")
public class Cita {
	@Id
	@Column(name = "ID_CITA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idcita;
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA_PACIENTE", nullable = false)
	private Persona personaPaciente;
	@ManyToOne
	@JoinColumn(name = "ID_MEDICO", nullable = false)
	private Medico medico;
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_REG", nullable = false)
	private Usuario usuarioReg;
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDate fechaReg;
	@Column(name = "FECHA_CITA", nullable = false)
	private LocalDate fechaCita;
	@Column(name = "HORA_INICIO", nullable = false)
	private String horaInicio;
	@Column(name = "HORA_FIN", nullable = false, length = 20)
	private String horaFin;
	@Column(name = "NUMERO_CARTA", nullable = true, length = 20)
	private String numero_carta;
	@Column(name = "COBERTURA", nullable = true)
	private BigDecimal cobertura;
	@Column(name = "NUMERO", nullable = false)
	private long numero;
}
