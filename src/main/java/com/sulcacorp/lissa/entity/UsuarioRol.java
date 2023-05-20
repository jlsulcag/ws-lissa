package com.sulcacorp.lissa.entity;

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

@Data
@Table(name = "USUARIO_ROL")
@Entity
public class UsuarioRol extends GenericEntity {
	
	private static final long serialVersionUID = 5203579611910941653L;

	@Id
	@Column(name = "ID_USUARIO_ROL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioRol;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIOROL_USUARIO"))
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIOROL_ROL"))
	private Rol rol;
		
}
