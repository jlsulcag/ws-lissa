package com.sulcacorp.lissa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sulcacorp.lissa.model.generic.GenericModel;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@ApiModel(description = "Informacion de Persona")
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSONA")
public class Persona extends GenericModel{
	
	private static final long serialVersionUID = -3944545346661730666L;

	@Id
	@Column(name = "ID_PERSONA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PERSONA", nullable = false, foreignKey = @ForeignKey(name="fk_persona_tipopersona"))
	private TipoPersona tipoPersona;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DOCUMENTO_IDENTIDAD", nullable = false, foreignKey = @ForeignKey(name="fk_persona_tipodocumento_identidad"))
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO_CIVIL", nullable = false, foreignKey = @ForeignKey(name="fk_persona_estadocivil"))
	private EstadoCivil estadoCivil;
	
	@Column(name = "NOMBRES", nullable = false, length = 100)
	private String nombres;
	
	@Column(name = "APELLIDO_PATERNO", nullable = false, length = 100)
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO", nullable = false, length = 100)
	private String apellidoMaterno;
	
	@Column(name = "NUMERO_DOCUMENTO_IDENTIDAD", nullable = false, length = 25)
	private String numeroDocumentoIdentidad;
	
	@Column(name = "FECHA_NACIMIENTO", nullable = true)
	private LocalDate fechaNacimiento;
	
	@Column(name = "FECHA_REGISTRO", nullable = false)
	private LocalDateTime fechaRegistro;
	
	@Column(name = "SEXO", nullable = true, length = 2)
	private String sexo;
	
	@Column(name = "DIRECCION", nullable = true, length = 200)
	private String direccion;
	
	@Column(name = "TELEFONO", nullable = true, length = 40)
	private String telefono;
	
	@Column(name = "CORREO_ELECTRONICO", nullable = true, length = 50)
	private String correoElectronico;
	
	@Column(name = "ESTADO", nullable = false, length = 1)
	private String estado;
	
}
