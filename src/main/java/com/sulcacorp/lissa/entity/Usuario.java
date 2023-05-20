package com.sulcacorp.lissa.entity;

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

import com.sulcacorp.lissa.entity.generic.GenericEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario extends GenericEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4553314403991496938L;

	@Id
	@Column(name ="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIO_PERSONA"))
	private Persona persona;
	
	@Column(name = "NOMBRE_USUARIO", nullable = false, length = 20)
	private String nombreUsuario;
	
	@Column(name = "CONTRASENIA", nullable = false, length = 500)
	private String contrasenia;
	
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDateTime fechaReg;
	
}
