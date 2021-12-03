package com.sulcacorp.lissa.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PACIENTE")
public class Paciente {
	@Id
	@Column(name = "ID_PACIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false, foreignKey = @ForeignKey(name="FK_PACIENTE_PERSONA"))
	private Persona persona;
		
	@Column(name = "LUGAR_NACIMIENTO", nullable = true, length = 100)
	private String lugarNacimiento;
	
	@Column(name = "LUGAR_PROCEDENCIA", nullable = true, length = 100)
	private String lugarProcedencia;
	
	@Column(name = "GRADO_INSTRUCCION", nullable = true, length = 100)
	private String gradoInstruccion;
	
	@Column(name = "OCUPACION", nullable = true, length = 100)
	private String ocupacion;
		
	@Column(name = "ESTADO", nullable = false)
	private int estado = 1;
}
