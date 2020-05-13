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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {
	@Id
	@Column(name ="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIO_PERSONA"))
	private Persona persona;
	@Column(name = "NOMBRE_USUARIO", nullable = false, length = 20)
	private String nombreUsuario;
	@Column(name = "DESCRIPCION", nullable = true, length = 120)
	private String descripcion;
	@Column(name = "CONTRASENIA", nullable = false, length = 500)
	private String contrasenia;
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDate fechaReg;
	@Column(name = "ESTADO", nullable = false)
	private int estado;
	
}
